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

import dev.lukasl.jwinkey.enums.VirtualKey;
import dev.lukasl.jwinkey.observables.KeyStateObservable;

public class JWinKey {
  public static void main(String[] args) {
    KeyStateObservable observable = KeyStateObservable.of(VirtualKey.VK_SNAPSHOT.getVirtualKeyCode());
    observable.subscribe(System.out::println);
  }
}
