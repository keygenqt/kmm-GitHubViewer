import {Route} from "react-router-dom";
import * as React from "react";
import {UserPage} from "../../pages";

export const RouteUserConf = {
    delay: 200,
    routes: {
        user: {
            path: '/',
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