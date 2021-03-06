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

package dev.lukasl.jwinkey;

import dev.lukasl.jwinkey.listener.UserInputEvent;
import dev.lukasl.jwinkey.listener.UserInputListener;

import java.awt.event.KeyEvent;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @see Thread
 */
public class UserInputInterceptor extends Thread {
  private final Set<Integer> keys = new HashSet<>(Arrays.asList(
    KeyEvent.VK_SHIFT, KeyEvent.VK_CONTROL, KeyEvent.VK_ALT
  ));
  private final Set<Integer> pressed = new HashSet<>();
  private final List<UserInputListener> keyListeners = new ArrayList<>();

  /**
   * @see Thread#Thread()
   */
  public UserInputInterceptor() {
    super();
  }

  /**
   * @see Thread#Thread(String)
   */
  public UserInputInterceptor(String name) {
    super(name);
  }

  /**
   * @see Thread#Thread(ThreadGroup, String)
   */
  public UserInputInterceptor(ThreadGroup group, String name) {
    super(group, name);
  }

  /**
   * Clears all keys that were pressed during the absence.
   */
  private void clearAbsentKeys() {
    this.keys.forEach(VirtualKey::stateOf);
  }

  /**
   * @param keyCode key code of the key to test
   * @return true whenever the key is marked as pressed for the {@link UserInputInterceptor}.
   */
  private boolean isPressed(int keyCode) {
    return this.pressed.contains(keyCode);
  }

  /**
   * @return true whenever any shift key is marked as pressed.
   */
  private boolean isShiftPressed() {
    return this.isPressed(VirtualKey.VK_SHIFT.getKeyCode())
      || this.isPressed(VirtualKey.VK_LEFT_SHIFT.getKeyCode())
      || this.isPressed(VirtualKey.VK_RIGHT_SHIFT.getKeyCode());
  }

  /**
   * @return true whenever any control key is marked as pressed.
   */
  private boolean isControlPressed() {
    return this.isPressed(VirtualKey.VK_CONTROL.getKeyCode())
      || this.isPressed(VirtualKey.VK_LEFT_CONTROL.getKeyCode())
      || this.isPressed(VirtualKey.VK_RIGHT_CONTROL.getKeyCode());
  }

  /**
   * @return true whenever any menu/alt key is marked as pressed.
   */
  private boolean isMenuPressed() {
    return this.isPressed(VirtualKey.VK_MENU.getKeyCode())
      || this.isPressed(VirtualKey.VK_LEFT_MENU.getKeyCode())
      || this.isPressed(VirtualKey.VK_RIGHT_MENU.getKeyCode());
  }

  /**
   * @param keyCode the involved key code
   * @return a new {@link KeyEvent} from the passed key code.
   */
  private UserInputEvent createEvent(int keyCode) {
    return new UserInputEvent(
      keyCode,
      this.isShiftPressed(),
      this.isControlPressed(),
      this.isMenuPressed()
    );
  }

  /**
   * Marks a key code as pressed.
   *
   * @param keyCode key code to be pressed
   */
  private void press(int keyCode) {
    this.pressed.add(keyCode);
    this.keyListeners.forEach((listener) -> listener.keyPressed(this.createEvent(keyCode)));
  }

  /**
   * Marks a key code as released.
   *
   * @param keyCode key code to be released
   */
  private void release(int keyCode) {
    this.pressed.remove(keyCode);
    this.keyListeners.forEach((listener) -> listener.keyReleased(this.createEvent(keyCode)));
  }

  /**
   * Register key codes, which the {@link UserInputInterceptor} listens to.
   *
   * @param keyCodes key codes to be added
   * @return the current instance of the {@link UserInputInterceptor}
   * @see UserInputInterceptor#registerKeyCodes(Collection)
   */
  public UserInputInterceptor registerKeyCodes(Integer... keyCodes) {
    Collections.addAll(this.keys, keyCodes);
    return this;
  }

  /**
   * Registers key codes, which the {@link UserInputInterceptor} listens to.
   *
   * @param keyCodes key codes to be added
   * @return the current instance of the {@link UserInputInterceptor}
   * @see UserInputInterceptor#registerKeyCodes(Integer...)
   */
  public UserInputInterceptor registerKeyCodes(Collection<Integer> keyCodes) {
    this.keys.addAll(keyCodes);
    return this;
  }

  /**
   * Registers {@link VirtualKey}s, which the {@link UserInputInterceptor} listens to.
   *
   * @param keys {@link VirtualKey}s to be added
   * @return the current instance of the {@link UserInputInterceptor}
   * @see UserInputInterceptor#registerKeyCodes(Collection)
   */
  public UserInputInterceptor registerVirtualKeys(VirtualKey... keys) {
    this.registerKeyCodes(Arrays.stream(keys).map(VirtualKey::getKeyCode).collect(Collectors.toList()));
    return this;
  }

  /**
   * Registers {@link VirtualKey}s, which the {@link UserInputInterceptor} listens to.
   *
   * @param keys {@link VirtualKey}s to be added
   * @return the current instance of the {@link UserInputInterceptor}
   * @see UserInputInterceptor#registerKeyCodes(Collection)
   */
  public UserInputInterceptor registerVirtualKeys(Collection<VirtualKey> keys) {
    this.registerKeyCodes(keys.stream().map(VirtualKey::getKeyCode).collect(Collectors.toList()));
    return this;
  }

  /**
   * Registers all available {@link VirtualKey}s.
   *
   * @return the current instance of the {@link UserInputInterceptor}
   * @see VirtualKey
   * @see UserInputInterceptor#registerVirtualKeys(VirtualKey...)
   * @see UserInputInterceptor#registerVirtualKeys(Collection)
   */
  public UserInputInterceptor registerAllVirtualKeys() {
    this.registerVirtualKeys(VirtualKey.values());
    return this;
  }

  /**
   * Adds new {@link UserInputListener}s to the {@link UserInputInterceptor}.
   *
   * @param listeners {@link UserInputListener}s to be added
   * @return the current instance of the {@link UserInputInterceptor}
   */
  public UserInputInterceptor addListeners(UserInputListener... listeners) {
    Collections.addAll(this.keyListeners, listeners);
    return this;
  }

  /**
   * Adds new {@link UserInputListener}s to the {@link UserInputInterceptor}.
   *
   * @param listeners {@link UserInputListener}s to be added
   */
  public UserInputInterceptor addListeners(Collection<UserInputListener> listeners) {
    this.keyListeners.addAll(listeners);
    return this;
  }

  /**
   * {@link Runnable#run} implementation of the {@link Thread}.
   * Runs the listening loop.
   */
  @Override
  public void run() {
    this.clearAbsentKeys();
    while (this.isAlive()) {
      this.keys.forEach((keyCode) -> {
        boolean pressed = VirtualKey.isPressed(keyCode);
        if (!pressed && this.isPressed(keyCode)) {
          this.release(keyCode);
          return;
        }
        if (pressed && !this.isPressed(keyCode)) {
          this.press(keyCode);
        }
      });
    }
  }
}
