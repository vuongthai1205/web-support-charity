import React, { createContext, useContext, useEffect } from 'react';
import useWebSocket from 'react-use-websocket';
import { toast } from 'react-toastify';
import { MyUserContext } from 'App';

const WebSocketContext = createContext(null);

export const useWebSocketContext = () => {
    return useContext(WebSocketContext);
};

export const WebSocketProvider = ({ children, wsUrl }) => {
    const [user, dispatch] = useContext(MyUserContext);
    const { sendMessage, lastMessage, readyState, getWebSocket } = useWebSocket(wsUrl, {
        onOpen: () => console.log('WebSocket opened.'),
        onClose: () => console.log('WebSocket closed.'),
        onMessage: (event) => {
            const dataFromServer = JSON.parse(event.data);
            console.log(dataFromServer, user);

            if (dataFromServer.to !== undefined && dataFromServer?.to === user?.username) {
                toast.success(dataFromServer.content);
                console.log('Thông báo');
            }
        },
        onError: (error) => console.error('WebSocket error:', error),
    });

    const value = {
        sendMessage,
        lastMessage,
        readyState,
        getWebSocket,
    };

    return <WebSocketContext.Provider value={value}>{children}</WebSocketContext.Provider>;
};
