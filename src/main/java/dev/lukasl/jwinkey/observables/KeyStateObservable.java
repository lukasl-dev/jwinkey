/*
 *    Copyright 2021 lukasl-dev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package dev.lukasl.jwinkey.observables;

import dev.lukasl.jwinkey.components.KeyStateUpdate;
import dev.lukasl.jwinkey.enums.KeyState;
import dev.lukasl.jwinkey.enums.VirtualKey;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class KeyStateObservable extends Observable<KeyStateUpdate> {
  private final ExecutorService executorService = Executors.newSingleThreadExecutor();
  private final Set<Integer> registered = new CopyOnWriteArraySet<>();
  private final Set<Integer> pressed = new CopyOnWriteArraySet<>();
  private final int delay;

  public KeyStateObservable register(@lombok.NonNull Collection<Integer> virtualKeyCodes) {
    this.registered.addAll(virtualKeyCodes);
    return this;
  }

  public KeyStateObservable register(Integer... virtualKeyCodes) {
    Collections.addAll(this.registered, virtualKeyCodes);
    return this;
  }

  public KeyStateObservable unregister(Integer... virtualKeyCodes) {
    this.registered.removeAll(Arrays.asList(virtualKeyCodes));
    return this;
  }

  public KeyStateObservable unregister(Collection<Integer> virtualKeyCodes) {
    this.registered.removeAll(virtualKeyCodes);
    return this;
  }

  public boolean isPressed(Integer... virtualKeyCodes) {
    return Arrays.stream(virtualKeyCodes).allMatch(this.pressed::contains);
  }

  public boolean isPressed(VirtualKey... virtualKeys) {
    return this.isPressed(Arrays.stream(virtualKeys).map(VirtualKey::getVirtualKeyCode).toArray(Integer[]::new));
  }

  private void press(int virtualKeyCode) {
    this.pressed.add(virtualKeyCode);
  }

  private void release(int virtualKeyCode) {
    this.pressed.remove(virtualKeyCode);
  }

  /**
   * Operator implementations (both source and intermediate) should implement this method that
   * performs the necessary business logic and handles the incoming {@link Observer}s.
   * <p>There is no need to call any of the plugin hooks on the current {@code Observable} instance or
   * the {@code Observer}; all hooks and basic safeguards have been
   * applied by {@link #subscribe(Observer)} before this method gets called.
   *
   * @param observer the incoming {@code Observer}, never {@code null}
   */
  @Override
  @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
  protected void subscribeActual(@NonNull Observer<? super KeyStateUpdate> observer) {
    this.executorService.submit(() -> {
      while (true) {
        this.registered.forEach((virtualKeyCode) -> {
          KeyState keyState = KeyState.getKeyState(virtualKeyCode);

          if (keyState == KeyState.RELEASED && this.isPressed(virtualKeyCode)) {
            this.release(virtualKeyCode);
            observer.onNext(new KeyStateUpdate(virtualKeyCode, KeyState.RELEASED));
            return;
          }

          if (keyState == KeyState.PRESSED && !this.isPressed(virtualKeyCode)) {
            this.press(virtualKeyCode);
            observer.onNext(new KeyStateUpdate(virtualKeyCode, KeyState.PRESSED));
          }
        });

        try {
          Thread.sleep(this.delay);
        } catch (InterruptedException e) {
          observer.onError(e);
        }
      }
    });
  }

  public static KeyStateObservable delayed(int delay, Integer... virtualKeyCodes) {
    return new KeyStateObservable(delay).register(virtualKeyCodes);
  }

  public static KeyStateObservable delayed(int delay, VirtualKey... virtualKeys) {
    return delayed(delay, Arrays.stream(virtualKeys).map(VirtualKey::getVirtualKeyCode).toArray(Integer[]::new));
  }

  public static KeyStateObservable of(Integer... virtualKeyCodes) {
    return delayed(10, virtualKeyCodes);
  }

  public static KeyStateObservable of(VirtualKey... virtualKeys) {
    return of(Arrays.stream(virtualKeys).map(VirtualKey::getVirtualKeyCode).toArray(Integer[]::new));
  }
}
