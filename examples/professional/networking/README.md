# Professional Networking Examples

These examples introduce Java networking with the standard library.

## Examples

- `HttpReadDemo.java`: reads text from a URL with timeouts.
- `SocketServerDemo.java`: starts a tiny local server.
- `SocketClientDemo.java`: connects to the local server and reads one line.

## Compile

```bash
javac examples/professional/networking/*.java
```

## Run

In one terminal:

```bash
java -cp examples/professional/networking SocketServerDemo
```

In another terminal:

```bash
java -cp examples/professional/networking SocketClientDemo
```

## Professional Warnings

- Networks fail: timeouts, DNS issues, unavailable servers, and partial responses are normal.
- Always set sensible timeouts.
- Avoid hard-coding production URLs in business logic.
- Do not log secrets that may appear in headers or responses.
