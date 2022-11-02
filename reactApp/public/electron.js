const {app, BrowserWindow, ipcMain} = require('electron')
const path = require("path");
const isDev = require("electron-is-dev");

let mainWindow;
let subWindow;

function createWindow() {

    mainWindow = new BrowserWindow({
        width: 2200,
        height: 880,
        minWidth: 1200,
        minHeight: 680
    });

    mainWindow.webContents.openDevTools();

    // mainWindow = new BrowserWindow({
    //     width: 1200,
    //     height: 680,
    //     minWidth: 1200,
    //     minHeight: 680
    // });

    mainWindow.loadURL(
        isDev
            ? "http://localhost:3001"
            : `file://${path.join(__dirname, "../build/index.html")}`
    );
    mainWindow.on("closed", () => (mainWindow = null));
    mainWindow.on("focus", () => {
        try {
            subWindow.close()
        } catch (e) {
            subWindow = null
        }
    });
    mainWindow.setMenuBarVisibility(false)
    mainWindow.webContents.setWindowOpenHandler(({url}) => {

        subWindow = new BrowserWindow({
            title: "GitHub loading...",
            modal: true,
            parent: mainWindow
        })

        subWindow.loadFile(path.join(__dirname, 'loading.html')).then(() => {
            subWindow.loadURL(url).then(() => {
                subWindow.webContents.on('will-redirect', () => {
                    if (url.includes("keygenqt.com")) {

                        mainWindow.loadURL(subWindow.webContents
                            .getURL()
                            .replace(
                                "https://kmm.keygenqt.com/oauth",
                                "http://localhost:3001"
                            ))

                        subWindow.close()
                        subWindow = null
                    }

                });
            })
        })

        return {action: 'deny'}
    })
}

app.on("ready", createWindow);
app.on("window-all-closed", () => {
    if (process.platform !== "darwin") {
        app.quit();
    }
});
app.on("activate", () => {
    if (mainWindow === null) {
        createWindow();
    }
});