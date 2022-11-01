// const contextBridge = require('electron').contextBridge;
// const ipcRenderer = require('electron').ipcRenderer;
//
// // White-listed channels.
// const ipc = {
//     'render': {
//         // From render to main.
//         'send': [],
//         // From main to render.
//         'receive': [
//             'login:id'
//         ],
//         // From render to main and back again.
//         'sendReceive': []
//     }
// };
//
// contextBridge.exposeInMainWorld(
//     // Allowed 'ipcRenderer' methods.
//     'ipcRender', {
//         send: (channel, args) => {
//             let validChannels = ipc.render.send;
//             if (validChannels.includes(channel)) {
//                 ipcRenderer.send(channel, args);
//             }
//         },
//         receive: (channel, listener) => {
//             let validChannels = ipc.render.receive;
//             if (validChannels.includes(channel)) {
//                 // Deliberately strip event as it includes `sender`
//                 ipcRenderer.on(channel, (event, ...args) => listener(...args));
//             }
//         },
//         invoke: (channel, args) => {
//             let validChannels = ipc.render.sendReceive;
//             if (validChannels.includes(channel)) {
//                 return ipcRenderer.invoke(channel, args);
//             }
//         }
//     }
// );