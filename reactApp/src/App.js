import logo from './logo.svg';
import './App.css';
import shared from 'shared';


function App() {

  const greeting = new shared.com.keygenqt.viewer.Greeting().greeting()
  const appConstants = shared.com.keygenqt.viewer.utils.AppConstants.APP.DEBUG_CREDENTIAL_LOGIN

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
          {appConstants}
        </p>
      </header>
    </div>
  );
}

export default App;
