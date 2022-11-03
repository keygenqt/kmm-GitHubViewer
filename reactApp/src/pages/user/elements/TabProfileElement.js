import * as React from 'react';
import {useCallback, useContext, useEffect, useRef} from 'react';
import {Avatar, Box, Card, CardActionArea, CardContent, CardMedia, Grid, Stack, Typography} from "@mui/material";
import {ConstantKMM, ConstantLottie, LanguageContext} from "../../../base";
import {TabBarElement} from "./TabBarElement";
import {ErrorElement} from "./ErrorElement";
import {LoadingElement} from "./LoadingElement";
import Lottie from "lottie-react";
import PropTypes from "prop-types";
import {Group, GroupAdd, HomeRepairService} from "@mui/icons-material";

let timeoutID

export function TabProfileElement(props) {

    const {t} = useContext(LanguageContext)

    const {
        models = [],
        user = null,
        updateModels = function (followers) {
        },
        updateUser = function (followers) {
        }
    } = props

    const itemsRef = useRef()
    const loadingRef = useRef()

    const modelsCard = []

    const [page, setPage] = React.useState(1);
    const [error, setError] = React.useState(null);
    const [end, setEnd] = React.useState(false);
    const [loading, setLoading] = React.useState(models.length === 0 || user === null);
    const [loadingPage, setLoadingPage] = React.useState(false);
    const [data, setData] = React.useState(models);
    const [modelUser, setModelUser] = React.useState(user);

    useEffect(() => {
        clearTimeout(timeoutID)
        timeoutID = setTimeout(() => {

            if (loading || loadingPage) {
                ConstantKMM.httpClient.get.followers(page).then(async (response) => {
                    const result = response.toArray()

                    if (result.length < ConstantKMM.appConstants.APP.PAGE_LIMIT || result.length === 0) {
                        setEnd(true)
                    }

                    if (loading) {

                        ConstantKMM.httpClient.get.user().then(async (user) => {
                            setLoading(false)
                            setData(result)
                            setModelUser(user)
                        }).catch(async (error) => {
                            setModelUser(null)
                            setLoadingPage(false)
                            setLoading(false)
                            setError(error)
                        });

                    } else {
                        setLoadingPage(false)
                        setData(data.concat(result))
                    }

                }).catch(async (error) => {
                    setLoadingPage(false)
                    setLoading(false)
                    setError(error)
                });
            }
        }, 500)
    }, [data, loading, loadingPage, page])

    // The scroll listener
    const handleScrollFollower = useCallback((event) => {
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
            div.addEventListener("scroll", handleScrollFollower)
        }
    }, [handleScrollFollower, loading, data])

    useEffect(() => {
        updateModels(data)
    }, [data, updateModels])

    useEffect(() => {
        updateUser(modelUser)
    }, [modelUser, updateUser])

    data.forEach((model, index) => {
        modelsCard.push(
            <ItemFollower
                key={`repo-item-${index}`}
                model={model}
            />
        )
    })

    return (
        <Stack sx={{
            width: '100%',
            height: '100%'
        }}>
            <TabBarElement
                title={""}
                loading={loading}
                onRefreshClick={() => {
                    setTimeout(() => {
                        setPage(1)
                        setLoading(true)
                    }, 350)
                }}
                onSettingsClick={() => {

                }}
                editTitle={"Edit profile"}
                editOnClick={() => {

                }}
            />

            {loading || error ? (
                error ? (
                    <ErrorElement
                        error={error}
                    />
                ) : (
                    <LoadingElement/>
                )
            ) : (
                <Grid container spacing={0} sx={{
                    height: 'calc(100% - 64px)'
                }}>

                    <Grid item xs={8} sx={{
                        height: '100%',
                        overflowY: 'auto',
                        paddingX: 2
                    }}>
                        <Typography gutterBottom variant="h6" component="div" color='text.primary' sx={{
                            paddingTop: 2
                        }}>
                            {t('profile.t_title_profile')}
                        </Typography>

                        <PageUser
                            model={modelUser}
                        />
                    </Grid>

                    <Grid ref={itemsRef} item xs={4} sx={{
                        p: 2,
                        height: '100%',
                        overflowY: 'auto'
                    }}>
                        <Stack
                            spacing={2}
                        >
                            <Typography gutterBottom variant="h6" color='text.primary' sx={{
                                marginBottom: '-16px'
                            }}>
                                Followers
                            </Typography>

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
                </Grid>
            )}
        </Stack>
    )
}

TabProfileElement.propTypes = {
    user: PropTypes.object,
    models: PropTypes.array.isRequired,
    updateModels: PropTypes.func.isRequired,
    updateUser: PropTypes.func.isRequired
};

function ItemFollower(props) {

    const {
        model,
    } = props

    return (
        <>
            <Box sx={{height: '5px'}}/>
            <Card variant="outlined" sx={{
                overflow: 'inherit',
                position: 'relative',
                transitionDuration: '300ms',
                transitionTimingFunction: "ease-in-out",
                transitionProperty: 'border',
            }}>
                <CardActionArea onClick={() => {
                    window.open(model.htmlUrl, '_blank')
                }}>
                    <CardMedia
                        component="img"
                        height="200"
                        image={model.avatarUrl}
                        alt="green iguana"
                        sx={{
                            position: 'absolute',
                            width: '40px',
                            height: '40px',
                            borderRadius: '50%',
                            left: 0,
                            right: 0,
                            top: '-20px',
                            boxShadow: 'rgb(0 0 0 / 10%) 0px 3px 5px -1px, rgb(0 0 0 / 4%) 0px 5px 8px 0px, rgb(0 0 0 / 2%) 0px 1px 14px 0px',
                            margin: 'auto',
                            zIndex: 99
                        }}
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div" sx={{
                            paddingTop: '8px',
                            textTransform: 'capitalize'
                        }}>
                            {model.login}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </>
    )
}

ItemFollower.propTypes = {
    model: PropTypes.object.isRequired,
};

function PageUser(props) {

    const {model} = props

    const {isLocEn} = useContext(LanguageContext)

    return (
        <Stack>
            <Box align={"center"} sx={{
                paddingTop: '13px',
                paddingBottom: '7px',
            }}>
                <Avatar sx={{
                    width: '100%',
                    borderRadius: '50px',
                    height: 300,
                    boxShadow: 'rgb(0 0 0 / 10%) 0px 3px 5px -1px, rgb(0 0 0 / 4%) 0px 5px 8px 0px, rgb(0 0 0 / 2%) 0px 1px 14px 0px',
                }} src={model.avatarUrl} alt={'Logo'}/>
            </Box>

            <Stack
                direction="row"
                spacing={3}
                justifyContent="space-between"
                alignItems="center"
                sx={{
                    p: 3,
                    paddingTop: 4
                }}
            >
                <Stack
                    spacing={1}
                    justifyContent="center"
                    alignItems="center"
                >
                    <Typography variant="body1" color='text.primary' sx={{
                        textTransform: 'capitalize'
                    }}>
                        Pub Repos
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <HomeRepairService fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {model.publicRepos}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                    justifyContent="center"
                    alignItems="center"
                >
                    <Typography variant="body1" color='text.primary' sx={{
                        textTransform: 'capitalize'
                    }}>
                        Followers
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <Group fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {model.followers}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                    justifyContent="center"
                    alignItems="center"
                >
                    <Typography variant="body1" color='text.primary' sx={{
                        textTransform: 'capitalize'
                    }}>
                        Following
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <GroupAdd fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {model.following}
                    </Typography>
                </Stack>
            </Stack>

            <Stack
                spacing={3}
                sx={{
                    backgroundColor: 'secondary.light',
                    borderRadius: '10px',
                    p: 3,
                    marginBottom: 3
                }}
            >
                {model.company ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            Company
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.company}
                        </Typography>
                    </Stack>
                ) : null}

                {model.blog ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            Blog
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.blog}
                        </Typography>
                    </Stack>
                ) : null}

                {model.location ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            Location
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.location}
                        </Typography>
                    </Stack>
                ) : null}

                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        Date created profile
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {ConstantKMM.platformHelper.dateFormat(
                            ConstantKMM.platformHelper.toTimestamp(model.createdAt),
                            isLocEn ? "en-EN" : "ru-RU"
                        )}
                    </Typography>
                </Stack>

                {model.bio ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            Bio
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.bio}
                        </Typography>
                    </Stack>
                ) : null}

            </Stack>
        </Stack>
    )
}

PageUser.propTypes = {
    model: PropTypes.object.isRequired,
};