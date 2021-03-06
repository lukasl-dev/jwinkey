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

package dev.lukasl.jwinkey.listener;

public abstract class UserInputListenerAdapter implements UserInputListener {
  /**
   * This method gets executed as soon a key got pressed.
   *
   * @param event {@link UserInputEvent} for the key press.
   */
  @Override
  public void keyPressed(UserInputEvent event) {
    // empty method body
  }

  /**
   * This method gets executed as soon a key got released.
   *
   * @param event {@link UserInputEvent} for the key release.
   */
  @Override
  public void keyReleased(UserInputEvent event) {
    // empty method body
  }
}
