import {Route} from "react-router-dom";
import {BaseLayout} from "../../layouts/BaseLayout";
import {FollowersPage, ProfilePage, ReposPage} from "../../pages";
import * as React from "react";

export const RouteConf = {
    delay: 200,
    routes: {
        home: {
            path: '/',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <BaseLayout
                            disablePadding={true}
                            pageClassName={'ReposPage'}
                        >
                            <ReposPage/>
                        </BaseLayout>
                    }
                />
            }
        },
        blog: {
            path: '/profile',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <BaseLayout
                            disablePadding={true}
                            pageClassName={'ProfilePage'}
                        >
                            <ProfilePage/>
                        </BaseLayout>
                    }
                />
            }
        },
        blogArticles: {
            path: '/followers',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <BaseLayout
                            disablePadding={true}
                            pageClassName={'FollowersPage'}
                        >
                            <FollowersPage/>
                        </BaseLayout>
                    }
                />
            }
        },
    },
}