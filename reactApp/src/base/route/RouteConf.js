import {Route} from "react-router-dom";
import * as React from "react";
import {OnboardingPage, SignInPage, WelcomePage} from "../../pages";
import {ConstantKMM} from "../constants/ConstantKMM";

export const RouteConf = {
    delay: 200,
    routes: {
        onboarding: {
            path: ConstantKMM.crossStorage.isOnboardingDone ? '/onboarding' : '/',
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
            path: ConstantKMM.crossStorage.isOnboardingDone ? '/' : '/welcome',
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