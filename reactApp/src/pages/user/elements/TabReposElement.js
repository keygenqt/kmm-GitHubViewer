import * as React from 'react';
import {useEffect} from 'react';
import {Avatar, Card, CardActionArea, CardActions, CardContent, Grid, Stack, Typography, useTheme} from "@mui/material";
import {ConstantKMM} from "../../../base";
import {SnackbarError} from "../../../components";
import {TabBarElement} from "./TabBarElement";
import {LoadingElement} from "./LoadingElement";
import CardHeader from '@mui/material/CardHeader';
import {CallSplit, Star, Visibility} from "@mui/icons-material";

export function TabReposElement() {

    const theme = useTheme()
    const [error, setError] = React.useState(null);
    const [loading, setLoading] = React.useState(true);
    const [isOrderASC, setIsOrderASC] = React.useState(ConstantKMM.crossStorage.isRepoOrder ?? false);
    const [data, setData] = React.useState(null);
    const [modelAction, setModelAction] = React.useState(null);

    useEffect(() => {
        if (data === null) {
            setLoading(true)
            ConstantKMM.httpClient.get.repos(1, isOrderASC).then(async (response) => {
                setData(response.toArray())
                setModelAction(response.toArray()[0])
                setLoading(false)
            }).catch(async (response) => {
                setData([])
                setError(response)
                setLoading(false)
            });
        }
    }, [data, isOrderASC])

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
                            setData(null)
                        }, 350)
                    }}
                    onRefreshClick={() => {
                        setTimeout(() => {
                            setData(null)
                        }, 350)
                    }}
                    onSettingsClick={() => {
                        console.log("ye")
                    }}
                />

                {loading || data == null ? (
                    <LoadingElement/>
                ) : (
                    <Grid container spacing={0} sx={{
                        height: 'calc(100% - 64px)'
                    }}>
                        <Grid item xs={4} sx={{
                            p: 2,
                            height: '100%',
                            overflowY: 'auto'
                        }}>
                            <Stack spacing={2}>
                                {data.map((model) => (
                                    <Card variant="outlined" key={`repo-${model.id}`} sx={{
                                        transitionDuration: '300ms',
                                        transitionTimingFunction: "ease-in-out",
                                        transitionProperty: 'border',
                                        ...(model.id !== modelAction.id ? {} : {
                                            border: '1px solid ' + theme.palette.primary.light,
                                        })
                                    }}>
                                        <CardActionArea onClick={() => {
                                            setModelAction(model)
                                        }}>
                                            <CardHeader
                                                avatar={
                                                    <Avatar aria-label="recipe">
                                                        R
                                                    </Avatar>
                                                }
                                                subheader="September 14, 2016"
                                                sx={{
                                                    paddingBottom: 0
                                                }}
                                            />

                                            <CardContent>
                                                <Typography gutterBottom variant="h5" component="div">
                                                    {model.name}
                                                </Typography>
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
                                            </CardActions>
                                        </CardActionArea>
                                    </Card>
                                ))}
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