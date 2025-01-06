# Server Application - Java Chat Program

This is a simple Java-based server program that implements basic text-based communication between a client and a server. The server listens on a specific port and allows message exchange between itself and a client.

## Features
- **Multithreaded Communication**: Separate threads for reading and writing messages.
- **Graceful Exit**: Closes connection cleanly when "exit" is sent by either party.
- **Console-Based Interaction**: Messages are read from and written to the console.

## How It Works
1. The server creates a `ServerSocket` listening on port `7777`.
2. It waits for a client to connect.
3. Once connected, it initializes:
   - A thread for reading messages from the client.
   - A thread for writing messages to the client.
4. Both threads run concurrently until "exit" is received or sent, at which point the connection is closed.

## Prerequisites
- Java Development Kit (JDK) installed.
- A client program to connect to the server.

## Usage

### 1. Clone the Repository
```bash
https://github.com/your-username/java-chat-server.git
cd java-chat-server
```

### 2. Compile the Program
```bash
javac Server.java
```

### 3. Run the Server
```bash
java Server
```

### 4. Connect a Client
Use a compatible client program to connect to the server on `localhost` at port `7777`. A sample client program is available [here](https://github.com/your-username/java-chat-client).

## Code Walkthrough
### Key Components

1. **Server Initialization**
   - A `ServerSocket` is created on port `7777`.
   - The server waits for a client to connect.

```java
server = new ServerSocket(7777);
socket = server.accept();
```

2. **Reading Messages**
   - Messages from the client are read using a `BufferedReader` in a separate thread.
   - If the message is "exit", the connection is closed.

```java
while (true) {
    String message = br.readLine();
    if (message.equalsIgnoreCase("exit")) {
        socket.close();
        break;
    }
    System.out.println(message);
}
```

3. **Writing Messages**
   - Messages are sent to the client using a `PrintWriter` in another thread.
   - If "exit" is typed, the connection is closed.

```java
BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
String message = br1.readLine();
pw.println(message);
if (message.equalsIgnoreCase("exit")) {
    socket.close();
    break;
}
```

## Example Output
### Server Console:
```
hello
server is created
client is connected
Reading Started
Client: Hello Server!
Client: How are you?
Server: I'm good, thank you.
exit
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

Feel free to contribute by submitting issues or pull requests. Happy coding!
