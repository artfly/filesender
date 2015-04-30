# filesender
Simple realization of file sender using java sockets

Client side contains following classes:
- MainApp<br>
main
- Client<br>
Used like controller
- Connector<br>
Establishes connection and closes it
- Sender<br>
Sends : 1. File name 2. Its size 3. Chunks and their sizes.<br>
Receives only final size of created file after sending. Checks received size and prints fail/success message.

Server side :
- MainApp
- Connector
- Server
- Receiver<br>
Server receives filename, file size and chunks with their sizes.
Stop working after receiving all chunks and sending file size.

To build projects simply run 
**gradle build**
in server(client) folder.
