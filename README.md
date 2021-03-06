# jwinkey

<div align="center">
  <a href="https://www.oracle.com/java/">
    <img
      src="https://img.shields.io/badge/Made%20with-Java-red"
    />
  </a>
  <a href="https://www.oracle.com/java/">
    <img
      src="https://jitpack.io/v/lukasl-dev/jwinkey.svg"
    />
  </a>
</div>

<br>

- [jwinkey](#jwinkey)
  - [What is `jwinkey`?](#what-is-jwinkey)
  - [Dependency](#dependency)
    - [Gradle](#gradle)
    - [Maven](#maven)
  - [Getting started](#getting-started)
    - [Create a new UserInputInterceptor](#create-a-new-userinputinterceptor)
    - [Register Keys](#register-keys)
    - [Register Listeners](#register-listeners)
    - [Start listening](#start-listening)
  - [Full example](#full-example)

---

## What is `jwinkey`?

`jwinkey` is a windowless/global keyboard and mouse listener for Windows written in Java.

**You are a Go developer? Check this out: <https://github.com/daspoet/gowinkey/>**

---

## Dependency

### Gradle

Add the [JitPack](https://jitpack.io/#lukasl-dev/jwinkey/v0.0.1) repository to your build file.

```groovy
repositories {
  maven { url = 'https://jitpack.io' }
}
```

Add the dependency to your build file.

```groovy
dependencies {
  implementation 'com.github.lukasl-dev:jwinkey:v0.0.1'
}
```

### Maven

Add the [JitPack](https://jitpack.io/#lukasl-dev/jwinkey/v0.0.1) repository to your build file.

```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Add the dependency to your build file.

```xml
<dependencies>
  <dependency>
    <groupId>com.github.lukasl-dev</groupId>
    <artifactId>jwinkey</artifactId>
    <version>v0.0.1</version>
  </dependency>
</dependencies>
```

---

## Getting started

### Create a new UserInputInterceptor

To use `jwinkey`, a `UserInputInterceptor` is needed. A `UserInputInterceptor` is nothing more than a "listener". It waits for keystrokes or mouse button presses and then executes the registered listeners.

**Important:** The UserInputIntercept does not block keystrokes. It only "listens in".

```java
UserInputInterceptor interceptor = new UserInputInterceptor();
```

### Register Keys

To avoid being flooded by keystrokes, the keys must be registered.

If you still need all the keys, you can register all the keys with the help of `registerAllVirtualKeys()`.

```java
interceptor.registerAllVirtualKeys();
```

If specific virtual key codes are to be registered, this can be done with `registerKeyCodes(Integer...)`.

```java
interceptor.registerKeyCodes(...);
```

This dependency provides an enum with the available virtual keys. To register one or more of them, they can be registered with `registerVirtualKeys(VirtualKey...)`.

See the [VirtualKey enum](https://github.com/lukasl-dev/jwinkey/blob/master/src/main/java/dev/lukasl/jwinkey/VirtualKey.java) and [Window's Virtual-Key Codes](https://docs.microsoft.com/en-us/windows/win32/inputdev/virtual-key-codes).

```java
interceptor.registerVirtualKeys(...);
```

### Register Listeners

To get keystrokes and mouse button presses, a `UserInputListener` must be registered.

```java
interceptor.addListeners(new UserInputListener() {
  @Override
  public void keyPressed(UserInputEvent event) {
    System.out.println("Key pressed: " + event);
  }

  @Override
  public void keyReleased(UserInputEvent event) {
    System.out.println("Key released: " + event);
  }
});
```

**Tip:** There is an abstract adapter implementation of `UserInputListener` called `UserInputListenerAdapter`.

**After any listeners and keys have been registered, the `UserInputInterceptor` must be started to receive events.**

### Start listening

To be able to intercept keystrokes, the `UserInputInterceptor` must be started. A `UserInterceptor` inherits from Thread, which is why it can simply be executed via `start()`.

```java
interceptor.start();
```

---

## Full example

```java
UserInputInterceptor interceptor = new UserInputInterceptor();
interceptor.registerVirtualKeys(VirtualKey.VK_C);
interceptor.addListeners(new UserInputListenerAdapter() {
  @Override
  public void keyPressed(UserInputEvent event) {
    if (event.getKeyCode() == VirtualKey.VK_C.getKeyCode() && event.isControlPressed()) {
      System.out.println("CTRL+C pressed.");
    }
  }
});
interceptor.start();
```
