import * as React from 'react';
import {useCallback, useEffect, useRef} from 'react';
import {
    Avatar,
    Box,
    Card,
    CardActionArea,
    CardActions,
    CardContent,
    Grid,
    Stack,
    Typography,
    useTheme
} from "@mui/material";
import {ConstantImages, ConstantKMM, ConstantLottie} from "../../../base";
import {SnackbarError} from "../../../components";
import {TabBarElement} from "./TabBarElement";
import {LoadingElement} from "./LoadingElement";
import {CallSplit, Lock, LockOpen, Star, Visibility} from "@mui/icons-material";
import Lottie from "lottie-react";

let timeoutID

function getIconLanguage(language) {
    let Icon = ConstantImages.languages.bash
    if (!language) {
        return <Icon/>
    }
    switch (language.toLowerCase()) {
        case ConstantKMM.appConstants.LANGUAGE.SWIFT:
            Icon = ConstantImages.languages.swift
            break;
        case ConstantKMM.appConstants.LANGUAGE.BASH:
            Icon = ConstantImages.languages.bash
            break;
        case ConstantKMM.appConstants.LANGUAGE.C:
            Icon = ConstantImages.languages.c
            break;
        case ConstantKMM.appConstants.LANGUAGE.CPLUSPLUS:
            Icon = ConstantImages.languages.cplusplus
            break;
        case ConstantKMM.appConstants.LANGUAGE.DART:
            Icon = ConstantImages.languages.dart
            break;
        case ConstantKMM.appConstants.LANGUAGE.ELIXIR:
            Icon = ConstantImages.languages.elixir
            break;
        case ConstantKMM.appConstants.LANGUAGE.ERLANG:
            Icon = ConstantImages.languages.erlang
            break;
        case ConstantKMM.appConstants.LANGUAGE.GROOVY:
            Icon = ConstantImages.languages.groovy
            break;
        case ConstantKMM.appConstants.LANGUAGE.HASKELL:
            Icon = ConstantImages.languages.haskell
            break;
        case ConstantKMM.appConstants.LANGUAGE.JAVA:
            Icon = ConstantImages.languages.java
            break;
        case ConstantKMM.appConstants.LANGUAGE.JAVASCRIPT:
            Icon = ConstantImages.languages.javascript
            break;
        case ConstantKMM.appConstants.LANGUAGE.KOTLIN:
            Icon = ConstantImages.languages.kotlin
            break;
        case ConstantKMM.appConstants.LANGUAGE.PHP:
            Icon = ConstantImages.languages.php
            break;
        case ConstantKMM.appConstants.LANGUAGE.PYTHON:
            Icon = ConstantImages.languages.python
            break;
        case ConstantKMM.appConstants.LANGUAGE.RUBY:
            Icon = ConstantImages.languages.ruby
            break;
        case ConstantKMM.appConstants.LANGUAGE.RUST:
            Icon = ConstantImages.languages.rust
            break;
        case ConstantKMM.appConstants.LANGUAGE.SCALA:
            Icon = ConstantImages.languages.scala
            break;
        default:
            Icon = ConstantImages.languages.bash
    }
    return <Icon/>
}

export function TabReposElement() {

    const itemsRef = useRef()
    const loadingRef = useRef()
    const theme = useTheme()

    const modelsCard = []

    const [page, setPage] = React.useState(1);
    const [error, setError] = React.useState(null);
    const [end, setEnd] = React.useState(false);
    const [loading, setLoading] = React.useState(true);
    const [loadingPage, setLoadingPage] = React.useState(false);
    const [isOrderASC, setIsOrderASC] = React.useState(ConstantKMM.crossStorage.isRepoOrder ?? false);
    const [data, setData] = React.useState([]);
    const [modelAction, setModelAction] = React.useState(null);

    useEffect(() => {
        clearTimeout(timeoutID)
        timeoutID = setTimeout(() => {
            if (loading || loadingPage) {
                ConstantKMM.httpClient.get.repos(page, isOrderASC).then(async (response) => {
                    const result = response.toArray()

                    if (result.length < ConstantKMM.appConstants.APP.PAGE_LIMIT || result.length === 0) {
                        setEnd(true)
                    }

                    if (loading) {
                        setLoading(false)
                        setData(result)
                        setModelAction(result[0])
                    } else {
                        setLoadingPage(false)
                        setData(data.concat(result))
                    }
                }).catch(async (response) => {
                    setLoadingPage(false)
                    setLoading(false)
                    setError(response)
                });
            }
        }, 500)
    }, [data, isOrderASC, loading, loadingPage, page])

    // The scroll listener
    const handleScroll = useCallback((event) => {
        if (!loadingPage) {
            const scrollEl = event.target
            const loadingEl = loadingRef.current

            if (scrollEl !== undefined && loadingEl !== undefined && loadingEl !== null) {
                if ((scrollEl.scrollTop + scrollEl.clientHeight) > ((loadingEl.offsetTop) - (loadingEl.clientHeight + 16))) {
                    setLoadingPage(true)
                    setPage(page + 1)
                }
            }
        }
    }, [loadingPage, page])

    useEffect(() => {
        if (itemsRef.current) {
            const div = itemsRef.current
            div.addEventListener("scroll", handleScroll)
        }
    }, [handleScroll, loading, data])


    data.forEach((model, index) => {
        modelsCard.push(
            <Card variant="outlined" key={`repo-${index}`} sx={{
                transitionDuration: '300ms',
                transitionTimingFunction: "ease-in-out",
                transitionProperty: 'border',
                ...(model.id !== modelAction.id ? {} : {
                    border: '1px solid ' + theme.palette.primary.light + '9e',
                })
            }}>
                <CardActionArea onClick={() => {
                    setModelAction(model)
                }}>
                    <CardContent>
                        <Stack
                            direction="row"
                            alignItems="center"
                            spacing={1}
                            sx={{
                                paddingBottom: model.desc ? 2 : 0
                            }}
                        >
                            <Avatar aria-label="languages" sx={{
                                '&': {
                                    border: '1px solid #eaeaea',
                                    backgroundColor: "transparent",
                                },
                                '& svg': {
                                    width: '24px',
                                }
                            }}>
                                {getIconLanguage(model.language)}
                            </Avatar>

                            <Typography gutterBottom variant="h5" component="div">
                                {model.name}
                            </Typography>
                        </Stack>

                        <Typography variant="body2" color="text.secondary">
                            {model.desc}
                        </Typography>
                    </CardContent>

                    <CardActions disableSpacing sx={{
                        paddingTop: 0,
                        paddingBottom: 2
                    }}>
                        <Stack
                            direction="row"
                            justifyContent="space-between"
                            alignItems="center"
                            spacing={2}
                            sx={{
                                width: '100%'
                            }}
                        >

                            <Stack
                                direction="row"
                                alignItems="center"
                                spacing={2}
                                sx={{
                                    paddingLeft: 1
                                }}
                            >
                                <Stack direction="row" spacing={1}>
                                    <Star fontSize={'small'}/>
                                    <Typography variant="body2" color="text.secondary">
                                        {model.stargazersCount}
                                    </Typography>
                                </Stack>

                                <Stack direction="row" spacing={1}>
                                    <CallSplit fontSize={'small'}/>
                                    <Typography variant="body2" color="text.secondary">
                                        {model.forks}
                                    </Typography>
                                </Stack>

                                <Stack direction="row" spacing={1}>
                                    <Visibility fontSize={'small'}/>
                                    <Typography variant="body2" color="text.secondary">
                                        {model.watchersCount}
                                    </Typography>
                                </Stack>
                            </Stack>

                        </Stack>

                        {model.isPrivate ? (
                            <Lock fontSize={'small'}/>
                        ) : (
                            <LockOpen fontSize={'small'}/>
                        )}

                    </CardActions>
                </CardActionArea>
            </Card>
        )
    })

    return (
        <>
            <SnackbarError error={error}/>
            <Stack sx={{
                width: '100%',
                height: '100%'
            }}>
                <TabBarElement
                    title={"Repos"}
                    loading={loading}
                    isOrderASC={isOrderASC}
                    onSortClick={() => {
                        const result = !isOrderASC
                        setIsOrderASC(result)
                        setTimeout(() => {
                            ConstantKMM.crossStorage.isRepoOrder = result
                            setPage(1)
                            setLoading(true)
                        }, 350)
                    }}
                    onRefreshClick={() => {
                        setTimeout(() => {
                            setPage(1)
                            setLoading(true)
                        }, 350)
                    }}
                    onSettingsClick={() => {
                        console.log("ye")
                    }}
                />

                {loading ? (
                    <LoadingElement/>
                ) : (
                    <Grid container spacing={0} sx={{
                        height: 'calc(100% - 64px)'
                    }}>
                        <Grid ref={itemsRef} item xs={4} sx={{
                            p: 2,
                            height: '100%',
                            overflowY: 'auto'
                        }}>
                            <Stack
                                spacing={2}
                            >
                                {modelsCard}

                                {end ? null : (
                                    <Box ref={loadingRef}>
                                        <Lottie style={{
                                            width: 60,
                                            margin: 'auto',
                                        }} animationData={ConstantLottie.loader}/>
                                    </Box>
                                )}

                            </Stack>
                        </Grid>
                        <Grid item xs={8} sx={{
                            p: 2,
                            height: '100%',
                            overflowY: 'auto'
                        }}>
                            <Typography gutterBottom variant="h5" component="div">
                                {modelAction.fullName}
                            </Typography>
                        </Grid>
                    </Grid>
                )}

            </Stack>
        </>
    )
}

TabReposElement.propTypes = {};