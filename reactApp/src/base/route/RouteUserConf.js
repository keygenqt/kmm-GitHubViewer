import {Route} from "react-router-dom";
import * as React from "react";
import {UserPage} from "../../pages";
import {SplashPage} from "../../pages/splash/SplashPage";

export const RouteUserConf = {
    delay: 200,
    routes: {
        splash: {
            path: '/',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <SplashPage/>
                    }
                />
            }
        },
        user: {
            path: '/user',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <UserPage/>
                    }
                />
            }
        },
    },
}