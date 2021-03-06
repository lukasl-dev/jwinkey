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

package dev.lukasl.jwinkey.components;

import dev.lukasl.jwinkey.enums.Modifier;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotkey {
  private final int id;
  private final int virtualKeyCode;
  private final Collection<Modifier> modifiers;

  @Builder
  public static Hotkey of(int id, int virtualKeyCode, Collection<Modifier> modifiers) {
    return new Hotkey(
      id != 0 ? id : ThreadLocalRandom.current().nextInt() + 1,
      virtualKeyCode,
      modifiers != null ? modifiers : new ArrayList<>()
    );
  }
}
