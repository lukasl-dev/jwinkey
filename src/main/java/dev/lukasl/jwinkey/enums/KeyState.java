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

package dev.lukasl.jwinkey.enums;

import com.sun.jna.platform.win32.User32;

public enum KeyState {
  PRESSED,
  RELEASED;

  private static final int KEY_PRESSED_MASK = 0x8000;

  private static KeyState fromKeyState(int keyState) {
    return (keyState & KEY_PRESSED_MASK) == KEY_PRESSED_MASK ? PRESSED : RELEASED;
  }

  public static KeyState getKeyState(int virtualKeyCode) {
    return fromKeyState(User32.INSTANCE.GetAsyncKeyState(virtualKeyCode));
  }
}
