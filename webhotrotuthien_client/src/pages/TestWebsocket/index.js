import { useEffect, useRef } from "react";

function TestWebsocket() {
    const connection = useRef(null)

  useEffect(() => {
    const socket = new WebSocket("ws://localhost:8080/webhotrotuthien/hello")

    // Connection opened
    socket.addEventListener("open", (event) => {
      socket.send({
        content: "test-websocket"
      })
    })

    // Listen for messages
    socket.addEventListener("message", (event) => {
      console.log("Message from server ", event.data)
    })
  }, [])
    return (
        <>
        hhih
            <input name="content" />
        </>
    );
}

export default TestWebsocket;
