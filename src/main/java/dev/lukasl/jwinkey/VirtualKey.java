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

import com.sun.jna.platform.win32.User32;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An enum implementation of the virtual key codes listed by windows.
 * <br>
 * <a href="https://docs.microsoft.com/en-us/windows/win32/inputdev/virtual-key-codes">
 * Virtual-Key Codes (Windows-API Documentation).
 * </a>
 */
@Getter
@ToString
@AllArgsConstructor
public enum VirtualKey {
  VK_LEFT_MOUSE_BUTTON(0x01, "Left mouse button"),
  VK_RIGHT_MOUSE_BUTTON(0x02, "Right mouse button"),
  VK_CANCEL(0x03, "Control-break processing"),
  VK_MIDDLE_MOUSE_BUTTON(0x04, "Middle mouse button (three-button mouse)"),
  VK_X1_MOUSE_BUTTON(0x05, "X1 mouse button"),
  VK_X2_MOUSE_BUTTON(0x06, "X2 mouse button"),
  VK_BACKSPACE(0x08, "BACKSPACE key"),
  VK_TAB(0x09, "TAB key"),
  VK_CLEAR(0x0C, "CLEAR key"),
  VK_RETURN(0x0D, "ENTER key"),
  VK_SHIFT(0x10, "SHIFT key"),
  VK_CONTROL(0x11, "CTRL key"),
  VK_MENU(0x12, "ALT key"),
  VK_PAUSE(0x13, "PAUSE key"),
  VK_CAPITAL(0x14, "CAPS LOCK key"),
  VK_KANA(0x15, "IME Kana mode"),
  VK_HANGUEL(0x15, "IME Hanguel mode (maintained for compatibility; use VK_HANGUL)"),
  VK_HANGUL(0x15, "IME Hangul mode"),
  VK_IME_ON(0x16, "IME On"),
  VK_JUNJA(0x17, "IME Junja mode"),
  VK_FINAL(0x18, "IME final mode"),
  VK_HANJA(0x19, "IME Hanja mode"),
  VK_KANJI(0x19, "IME Kanji mode"),
  VK_IME_OFF(0x1A, "IME Off"),
  VK_ESCAPE(0x1B, "ESC key"),
  VK_CONVERT(0x1C, "IME convert"),
  VK_NON_CONVERT(0x1D, "IME nonconvert"),
  VK_ACCEPT(0x1E, "IME accept"),
  VK_MODE_CHANGE(0x1F, "IME mode change request"),
  VK_SPACE(0x20, "SPACEBAR"),
  VK_PRIOR(0x21, "PAGE UP key"),
  VK_NEXT(0x22, "PAGE DOWN key"),
  VK_END(0x23, "END key"),
  VK_HOME(0x24, "HOME key"),
  VK_LEFT(0x25, "LEFT ARROW key"),
  VK_UP(0x26, "UP ARROW key"),
  VK_RIGHT(0x27, "RIGHT ARROW key"),
  VK_DOWN(0x28, "DOWN ARROW key"),
  VK_SELECT(0x29, "SELECT key"),
  VK_PRINT(0x2A, "PRINT key"),
  VK_EXECUTE(0x2B, "EXECUTE key"),
  VK_SNAPSHOT(0x2C, "PRINT SCREEN key"),
  VK_INSERT(0x2D, "INS key"),
  VK_DELETE(0x2E, "DEL key"),
  VK_HELP(0x2F, "HELP key"),
  VK_0(0x30, "0 key"),
  VK_1(0x31, "1 key"),
  VK_2(0x32, "2 key"),
  VK_3(0x33, "3 key"),
  VK_4(0x34, "4 key"),
  VK_5(0x35, "5 key"),
  VK_6(0x36, "6 key"),
  VK_7(0x37, "7 key"),
  VK_8(0x38, "9 key"),
  VK_9(0x39, "9 key"),
  VK_A(0x41, "A key"),
  VK_B(0x42, "B key"),
  VK_C(0x43, "C key"),
  VK_D(0x44, "D key"),
  VK_E(0x45, "E key"),
  VK_F(0x46, "F key"),
  VK_G(0x47, "G key"),
  VK_H(0x48, "H key"),
  VK_I(0x49, "I key"),
  VK_J(0x4A, "J key"),
  VK_K(0x4B, "K key"),
  VK_L(0x4C, "L key"),
  VK_M(0x4D, "M key"),
  VK_N(0x4E, "N key"),
  VK_O(0x4F, "O key"),
  VK_P(0x50, "P key"),
  VK_Q(0x51, "Q key"),
  VK_R(0x52, "R key"),
  VK_S(0x53, "S key"),
  VK_T(0x54, "T key"),
  VK_U(0x55, "U key"),
  VK_V(0x56, "V key"),
  VK_W(0x57, "W key"),
  VK_X(0x58, "X key"),
  VK_Y(0x59, "Y key"),
  VK_Z(0x5A, "Z key"),
  VK_LEFT_WIN(0x5B, "Left Windows key (Natural keyboard)"),
  VK_RIGHT_WIN(0x5C, "Right Windows key (Natural keyboard)"),
  VK_APPS(0x5D, "Applications key (Natural keyboard)"),
  VK_SLEEP(0x5F, "Computer Sleep key"),
  VK_NUMPAD0(0x60, "Numeric keypad 0 key"),
  VK_NUMPAD1(0x61, "Numeric keypad 1 key"),
  VK_NUMPAD2(0x62, "Numeric keypad 2 key"),
  VK_NUMPAD3(0x63, "Numeric keypad 3 key"),
  VK_NUMPAD4(0x64, "Numeric keypad 4 key"),
  VK_NUMPAD5(0x65, "Numeric keypad 5 key"),
  VK_NUMPAD6(0x66, "Numeric keypad 6 key"),
  VK_NUMPAD7(0x67, "Numeric keypad 7 key"),
  VK_NUMPAD8(0x68, "Numeric keypad 8 key"),
  VK_NUMPAD9(0x69, "Numeric keypad 9 key"),
  VK_MULTIPLY(0x6A, "Multiply key"),
  VK_ADD(0x6B, "Add key"),
  VK_SEPARATOR(0x6C, "Separator key"),
  VK_SUBTRACT(0x6D, "Subtract key"),
  VK_DECIMAL(0x6E, "Decimal key"),
  VK_DIVIDE(0x6F, "Divice key"),
  VK_F1(0x70, "F1 key"),
  VK_F2(0x71, "F2 key"),
  VK_F3(0x72, "F3 key"),
  VK_F4(0x73, "F4 key"),
  VK_F5(0x74, "F5 key"),
  VK_F6(0x75, "F6 key"),
  VK_F7(0x76, "F7 key"),
  VK_F8(0x77, "F8 key"),
  VK_F9(0x78, "F9 key"),
  VK_F10(0x79, "F10 key"),
  VK_F11(0x7A, "F11 key"),
  VK_F12(0x7B, "F12 key"),
  VK_F13(0x7C, "F13 key"),
  VK_F14(0x7D, "F14 key"),
  VK_F15(0x7E, "F15 key"),
  VK_F16(0x7F, "F16 key"),
  VK_F17(0x80, "F17 key"),
  VK_F18(0x81, "F18 key"),
  VK_F19(0x82, "F19 key"),
  VK_F20(0x83, "F20 key"),
  VK_F21(0x84, "F21 key"),
  VK_F22(0x85, "F22 key"),
  VK_F23(0x86, "F23 key"),
  VK_F24(0x87, "F24 key"),
  VK_NUMLOCK(0x90, "NUM LOCK key"),
  VK_SCROLL(0x91, "SCROLL LOCK key"),
  VK_OEM_SPECIFIC_1(0x92, "OEM specific"),
  VK_OEM_SPECIFIC_2(0x93, "OEM specific"),
  VK_OEM_SPECIFIC_3(0x94, "OEM specific"),
  VK_OEM_SPECIFIC_4(0x95, "OEM specific"),
  VK_OEM_SPECIFIC_5(0x96, "OEM specific"),
  VK_LEFT_SHIFT(0xA0, "Left SHIFT key"),
  VK_RIGHT_SHIFT(0xA1, "Right SHIFT key"),
  VK_LEFT_CONTROL(0xA2, "Left CONTROL key"),
  VK_RIGHT_CONTROL(0xA3, "Right CONTROL key"),
  VK_LEFT_MENU(0xA4, "Left MENU key"),
  VK_RIGHT_MENU(0xA5, "Right MENU key"),
  VK_BROWSER_BACK(0xA6, "Browser Back key"),
  VK_BROWSER_FORWARD(0xA7, "Browser Forward key"),
  VK_BROWSER_REFRESH(0xA8, "Browser Refresh key"),
  VK_BROWSER_STOP(0xA9, "Browser Stop key"),
  VK_BROWSER_SEARCH(0xAA, "Browser Search key"),
  VK_BROWSER_FAVORITES(0xAB, "Browser Favorites key"),
  VK_BROWSER_HOME(0x4C, "Browser Start and Home key"),
  VK_VOLUME_MUTE(0xAD, "Volume Mute key"),
  VK_VOLUME_DOWN(0xAE, "Volume Down key"),
  VK_VOLUME_UP(0xAF, "Volume Up key"),
  VK_MEDIA_NEXT_TRACK(0xB0, "Next Track key"),
  VK_MEDIA_PREVIOUS_TRACK(0xB1, "Previous Track key"),
  VK_MEDIA_STOP(0xB2, "Stop Media key"),
  VK_MEDIA_PLAY_PAUSE(0xB3, "Play/Pause Media key"),
  VK_LAUNCH_MAIL(0xB4, "Start Mail key"),
  VK_LAUNCH_MEDIA_SELECT(0xB5, "Select Media key"),
  VK_LAUNCH_APP1(0xB6, "Start Application 1 key"),
  VK_LAUNCH_APP2(0xB7, "Start Application 2 key"),
  VK_OEM_1(0xBA, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the ';:' key"),
  VK_OEM_PLUS(0xBB, "For any country/region, the '+' key"),
  VK_OEM_COMMA(0xBC, "For any country/region, the ',' key"),
  VK_OEM_MINUS(0xBD, "For any country/region, the '-' key"),
  VK_OEM_PERIOD(0xBE, "For any country/region, the '.' key"),
  VK_OEM_2(0xBF, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '/?' key"),
  VK_OEM_3(0xC0, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '`~' key"),
  VK_OEM_4(0xDB, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '[{' key"),
  VK_OEM_5(0xDC, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the '\\|' key"),
  VK_OEM_6(0xDD, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the ']}' key"),
  VK_OEM_7(0xDE, "Used for miscellaneous characters; it can vary by keyboard. For the US standard keyboard, the 'single-quote/double-quote' key"),
  VK_OEM_8(0xDF, "Used for miscellaneous characters; it can vary by keyboard."),
  VK_OEM_SPECIFIC_7(0xE1, "OEM specific"),
  VK_OEM_102(0xE2, "Either the angle bracket key or the backslash key on the RT 102-key keyboard"),
  VK_OEM_SPECIFIC_8(0xE3, "OEM specific"),
  VK_OEM_SPECIFIC_9(0xE4, "OEM specific"),
  VK_PROCESS(0xE5, "IME PROCESS key"),
  VK_OEM_SPECIFIC_10(0xE6, "OEM specific"),
  VK_PACKET(0xE7, "Used to pass Unicode characters as if they were keystrokes. The VK_PACKET key is the low word of a 32-bit Virtual Key value used for non-keyboard input methods. For more information, see Remark in KEYBDINPUT, SendInput, WM_KEYDOWN, and WM_KEYUP"),
  VK_OEM_SPECIFIC_11(0xE9, "OEM specific"),
  VK_OEM_SPECIFIC_12(0xEA, "OEM specific"),
  VK_OEM_SPECIFIC_13(0xEB, "OEM specific"),
  VK_OEM_SPECIFIC_14(0xEC, "OEM specific"),
  VK_OEM_SPECIFIC_15(0xEF, "OEM specific"),
  VK_OEM_SPECIFIC_16(0xF0, "OEM specific"),
  VK_OEM_SPECIFIC_17(0xF1, "OEM specific"),
  VK_OEM_SPECIFIC_18(0xF2, "OEM specific"),
  VK_OEM_SPECIFIC_19(0xF3, "OEM specific"),
  VK_OEM_SPECIFIC_20(0xF4, "OEM specific"),
  VK_OEM_SPECIFIC_21(0xF5, "OEM specific"),
  VK_ATTN(0xF6, "Attn key"),
  VK_CRSEL(0xF7, "CrSel key"),
  VK_EXSEL(0xF8, "ExSel key"),
  VK_EREOF(0xF9, "Erase EOF key"),
  VK_PLAY(0xFA, "Play key"),
  VK_ZOOM(0xFB, "Zoom key"),
  VK_NONAME(0xFC, "Reserved"),
  VK_PA1(0xFD, "PA1 key"),
  VK_OEM_CLEAR(0xFE, "Clear key");

  /**
   * The mask is conjugated with a key state to check whether a key has been pressed.
   */
  public static final int MASK_KEY_PRESSED = 0x8000;

  private final int keyCode;
  private final String description;

  /**
   * @return the name of the enum constant without the 'VK_' prefix.
   */
  public String strip() {
    return this.name().substring("VK_".length());
  }

  /**
   * @return a collected list of the key codes of all enum constants.
   */
  public static Collection<Integer> keyCodes() {
    return Arrays.stream(values())
      .map((vKey) -> vKey.keyCode)
      .collect(Collectors.toList());
  }

  /**
   * @param keyCode the key code from which the state is to be returned
   * @return the state of the passed key code
   */
  public static int stateOf(int keyCode) {
    return User32.INSTANCE.GetAsyncKeyState(keyCode);
  }

  /**
   * @param keyCode key code to test
   * @return true whenever user32 marked the passed key code as pressed.
   */
  public static boolean isPressed(int keyCode) {
    return (stateOf(keyCode) & MASK_KEY_PRESSED) == MASK_KEY_PRESSED;
  }

  /**
   * @param keyCode key code to find
   * @return an optional {@link VirtualKey}, which must have the same key code as the one passed.
   */
  public static Optional<VirtualKey> fromKeyCode(int keyCode) {
    return Arrays.stream(values())
      .filter((vKey) -> vKey.keyCode == keyCode)
      .findFirst();
  }
}
