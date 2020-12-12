import React from 'react'
import { Drawer as MuiDrawer, List as MuiList, ListItem, ListItemText } from '@material-ui/core'
import styled from 'styled-components'
import classNames from 'classnames'
import PerfectScrollbar from 'react-perfect-scrollbar'
import { withStyles } from '@material-ui/core/styles'
import { rgba, darken } from 'polished'
import { grey } from '@material-ui/core/colors'
import { NavLink } from 'react-router-dom'

const Drawer = styled(MuiDrawer)``

const Scrollbar = styled(PerfectScrollbar)`
  background-color: transparent;
  border-right: 1px solid rgba(0, 0, 0, 0.12);
`

const Items = styled.div`
  padding-top: ${(props) => props.theme.spacing(2.5)}px;
  padding-bottom: ${(props) => props.theme.spacing(2.5)}px;
`

const List = styled(MuiList)``

const drawerWidth = 240

const styles = theme => ({
  appFrame: {
    zIndex: 1,
    overflow: 'hidden',
    height: '100vh'
  },
  appBar: {
    position: 'fixed',
    width: '100%',
    zIndex: 1400
  },
  menuButton: {
    marginLeft: 12,
    marginRight: 20
  },
  drawerPaper: {
    position: 'fixed',
    width: drawerWidth,
    borderRadius: 0,
    borderTop: 'none',
    borderBottom: 'none',
    zIndex: 1,
    paddingTop: theme.spacing(15), // push content down to fix scrollbar position
    height: `calc(100% - ${theme.spacing(0)}px)`,// subtract appbar height
    backgroundColor: '#233044'
  },
  drawerContent: {
    overflow: 'auto',
    display: 'flex',
    flexDirection: 'column'
  },
  contentWrapper: {
    overflow: 'auto',
    position: 'fixed',
    top: theme.spacing(15),
    height: 'calc(100% - 64px)', // Subtract width of header
    backgroundColor: theme.palette.background.default,
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen
    })
  },
  'content-left': {
    marginLeft: drawerWidth
  },
  contentShift: {
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.easeOut,
      duration: theme.transitions.duration.enteringScreen
    })
  },
  content: {
    padding: theme.spacing(3),
    marginLeft: drawerWidth
  }
})

const Link = styled(ListItem)<{
    component: typeof NavLink;
    exact: boolean;
    to: string;
}>`
  padding-left: ${(props) => props.theme.spacing(2)}px;
  padding-top: ${(props) => props.theme.spacing(2)}px;
  padding-bottom: ${(props) => props.theme.spacing(2)}px;

  span {
    color: ${(props) => rgba(grey[200], 0.7)};
  }

  &:hover span {
    color: ${(props) => rgba(grey[200], 0.9)};
  }

  &:hover {
    background-color: ${(props) =>
    darken(0.015, '#233044')};
  }

  &.active {
    background-color: ${(props) =>
    darken(0.03, '#233044')};

    span {
      color: ${(props) => grey[200]};
    }
  }
`

const LinkText = styled(ListItemText)`
  color: ${(props) => grey[200]};
  span {
    font-size: ${(props) => props.theme.typography.body1.fontSize}px;
  }
  margin-top: 0;
  margin-bottom: 0;
`

class AdminMenu extends React.Component<any, any> {
  render () {
    const { classes } = this.props
    return (
        <div className={classes.appFrame}>
            <Drawer variant="permanent" anchor="left" classes={{
              paper: this.props.classes.drawerPaper
            }}>
                <Scrollbar>
                    <List disablePadding>
                        <Items>
                            <Link button exact component={NavLink} to={'/admin'}>
                                <LinkText>General Settings</LinkText>
                            </Link>
                            <Link button exact component={NavLink} to={'/admin/projects'}>
                                <LinkText>Manage Projects</LinkText>
                            </Link>
                            <Link button exact component={NavLink} to={'/admin/users'}>
                                <LinkText>Manage Users</LinkText>
                            </Link>
                        </Items>
                    </List>
                </Scrollbar>
            </Drawer>
            <div
                className={classNames(classes.contentWrapper, {
                  [classes.contentShift]: true,
                  [classes['content-left']]: true
                })}
            />
            <div className={classes.content}>
                {this.props.children}
            </div>
        </div>
    )
  }
}

// @ts-ignore
export default withStyles(styles, { withTheme: true })(AdminMenu)
