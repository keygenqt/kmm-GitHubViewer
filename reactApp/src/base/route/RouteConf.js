import {Route} from "react-router-dom";
import * as React from "react";
import {OnboardingPage, SignInPage, WelcomePage} from "../../pages";
import {SplashPage} from "../../pages/splash/SplashPage";

export const RouteConf = {
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
        onboarding: {
            path: '/onboarding',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <OnboardingPage/>
                    }
                />
            }
        },
        welcome: {
            path: '/welcome',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <WelcomePage/>
                    }
                />
            }
        },
        signIn: {
            path: '/signIn',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <SignInPage/>
                    }
                />
            }
        },
    },
}