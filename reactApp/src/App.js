import logo from './logo.svg';
import './App.css';
import shared from 'shared';

function App() {

  const greeting = new shared.com.keygenqt.viewer.Greeting().greeting()
  const appConstants = shared.com.keygenqt.viewer.utils.AppConstants
  const appHelper = shared.com.keygenqt.viewer.utils.AppHelper
  const crossStorage = new shared.com.keygenqt.viewer.data.storage.CrossStorage(localStorage)
  const appHttpClient = new shared.com.keygenqt.viewer.AppHttpClientJS("asdasdasd")

  appHttpClient.rockets().then(async (response) => {
    console.log("Success")
    console.log(response)
  }).catch(async (response) => {
    console.log("Error")
    console.log(response)
  });


  let uuid = require("uuid");
  crossStorage.authToken = uuid.v4()

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <div>
          {greeting}
        </div>
        <p>
          {appConstants.APP.DEBUG_CREDENTIAL_LOGIN}
        </p>
        <div>
          {appHelper.getDynamicLink("oauth")}
        </div>
        <p>
          AuthToken: {crossStorage.authToken}
        </p>
        <div>
          {shared.com.keygenqt.viewer.utils.PlatformHelper.dateFormat(1667165918,"ru-RU")}
        </div>
      </header>
    </div>
  );
}

export default App;
