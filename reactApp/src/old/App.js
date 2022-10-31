import logo from './logo.svg';
import './App.css';
import shared from 'shared';
import {useState} from "react";

function App() {

  const [rocketsCount, setRocketsCount] = useState(0)

  const greeting = new shared.com.keygenqt.viewer.Greeting().greeting()
  const appConstants = shared.com.keygenqt.viewer.utils.AppConstants
  const appHelper = shared.com.keygenqt.viewer.utils.AppHelper
  const crossStorage = new shared.com.keygenqt.viewer.data.storage.CrossStorage(localStorage)
  const appHttpClient = new shared.com.keygenqt.viewer.services.AppHttpClientJS("")

  appHttpClient.get.rockets().then(async (response) => {
    setRocketsCount(response.toArray().length)
  }).catch(async (response) => {
    console.log("Error")
    console.log(response)
  });

  let uuid = require("uuid");
  crossStorage.authToken = uuid.v4()

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" width={200} />
        <div>
          <span style={{color: "gray"}}>Shared cross platform fun: </span>
          {greeting}
        </div>
        <p>
          <span style={{color: "gray"}}>Shared const: </span>
          {appConstants.APP.DEBUG_CREDENTIAL_LOGIN}
        </p>
        <div>
          <span style={{color: "gray"}}>Shared environment: </span>
          {appHelper.getDynamicLink("oauth")}
        </div>
        <p>
          <span style={{color: "gray"}}>Shared store token: </span>
           {crossStorage.authToken}
        </p>
        <div>
          <span style={{color: "gray"}}>Shared format date: </span>
           {shared.com.keygenqt.viewer.utils.PlatformHelper.dateFormat(1667165918,"ru-RU")}
        </div>
        <p>
          <span style={{color: "gray"}}>Shared response: </span>
          {rocketsCount} rockets
        </p>
      </header>
    </div>
  );
}

export default App;
