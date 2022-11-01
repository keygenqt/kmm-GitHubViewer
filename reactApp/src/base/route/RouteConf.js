import {Route} from "react-router-dom";
import * as React from "react";
import {OnboardingPage, SignInPage, UserPage, WelcomePage} from "../../pages";

export const RouteConf = {
    delay: 200,
    routes: {
        user: {
            path: '/onboarding',
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
        onboarding: {
            path: '/',
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