import shared from "shared";

const crossStorage = new shared.com.keygenqt.viewer.data.storage.CrossStorage(localStorage)
const greeting = new shared.com.keygenqt.viewer.Greeting().greeting()
const appHelper = shared.com.keygenqt.viewer.utils.AppHelper
const appConstants = shared.com.keygenqt.viewer.utils.AppConstants
const httpClient = new shared.com.keygenqt.viewer.services.AppHttpClientJS(crossStorage.authToken)

/**
 * Apps constants KMM module
 */
export const ConstantKMM = {
    greeting: greeting,
    appHelper: appHelper,
    appConstants: appConstants,
    crossStorage: crossStorage,
    httpClient: httpClient
};