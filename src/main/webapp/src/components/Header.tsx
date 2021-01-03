import React from 'react'
import { useSelector } from 'react-redux'
import { fade, makeStyles } from '@material-ui/core/styles'
import SearchIcon from '@material-ui/icons/Search'
import styled from 'styled-components'
import { ListItem, Box as MuiBox, Typography, InputBase, Link as MuiLink } from '@material-ui/core'
import { Layers } from 'react-feather'
import { spacing } from '@material-ui/system'
import { darken } from 'polished'
import IssueMenu from './header/IssueMenu'
import MyProfileMenu from './header/MyProfileMenu'
import { NewIssueButton } from './header/NewIssueButton'
import { isAuthenticated } from '../helpers'
import AdminMenu from './header/AdminMenu'
import { blue } from '@material-ui/core/colors'
import BoardMenu from "./header/BoardMenu";

const useStyles = makeStyles((theme) => ({
  box: {
    zIndex: 100
  },
  grow: {
    flexGrow: 1
  },
  menu: {
    backgroundColor: '#fff'
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block'
    }
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: fade(theme.palette.common.white, 0.25)
    },
    marginRight: theme.spacing(2),
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(3),
      width: 'auto'
    }
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center'
  },
  inputRoot: {
    color: 'inherit'
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('md')]: {
      width: '20ch'
    }
  },
  sectionDesktop: {
    display: 'none',
    [theme.breakpoints.up('md')]: {
      display: 'flex'
    }
  },
  sectionMobile: {
    display: 'flex',
    [theme.breakpoints.up('md')]: {
      display: 'none'
    }
  }
}))

const Search = styled.div`
  border-radius: 2px;
  background-color: ${props => props.theme.header.background};
  display: none;
  position: relative;
  width: 100%;
  color: ${props => props.theme.header.color}

  &:hover {
    background-color: ${props => darken(0.05, props.theme.header.background)};
  }

  ${props => props.theme.breakpoints.up('md')} {
    display: block;
  }
`

const WhiteSearchIcon = styled(SearchIcon)`
    color: ${props => props.theme.header.search.color}
`

const Box = styled(MuiBox)(spacing)

const Brand = styled(ListItem)<{button?: boolean}>`
  font-size: ${props => props.theme.typography.h5.fontSize};
  font-weight: ${props => props.theme.typography.fontWeightMedium};
  color: ${props => props.theme.color};
  background-color: ${props => props.theme.header.background};
  font-family: ${props => props.theme.typography.fontFamily};
  padding-left: ${props => props.theme.spacing(6)}px;
  padding-right: ${props => props.theme.spacing(6)}px;
  cursor: default;
  &:hover {
    background-color: ${props => props.theme.header.background};
  }
`

const BrandIcon = styled(Layers)`
  margin-right: ${props => props.theme.spacing(2)}px;
  color: ${props => props.theme.header.brand.color};
`

const Link = styled(MuiLink)`
  color: ${props => props.theme.header.brand.color};
`

const SearchIconWrapper = styled.div`
  width: 50px;
  height: 100%;
  position: absolute;
  pointer-events: none;
  display: flex;
  align-items: center;
  justify-content: center;

  svg {
    width: 22px;
    height: 22px;
  }
`

const Input = styled(InputBase)`
  color: inherit;
  width: 100%;

  > input {
    color: ${props => props.theme.header.search.color};
    padding-top: ${props => props.theme.spacing(2.5)}px;
    padding-right: ${props => props.theme.spacing(2.5)}px;
    padding-bottom: ${props => props.theme.spacing(2.5)}px;
    padding-left: ${props => props.theme.spacing(12)}px;
    width: 160px;
  }
`

function PrimarySearchAppBar ({ initData }) {
  const classes = useStyles()

  const stateFn = state => state.init
  const init = useSelector(stateFn)

  return (
        <Box display="flex" bgcolor={blue[900]} p={2} alignItems="center" className={classes.box}>
            <Typography className={classes.title} noWrap color={'secondary'}>
                <Brand button>
                    <BrandIcon /> <Box ml={1}><Link href={'/'}>{init.publicName}</Link></Box>
                </Brand>
            </Typography>
            <Box>
                <Search>
                    <SearchIconWrapper>
                        <WhiteSearchIcon/>
                    </SearchIconWrapper>
                    <Input placeholder="Search issues" />
                </Search>
            </Box>
            { isAuthenticated() &&
            <Box>
                <NewIssueButton />
            </Box>
            }
            <Box flexGrow={1} textAlign="right">
                <IssueMenu />
                { initData.user?.admin &&
                <AdminMenu />
                }
                <BoardMenu />
                <MyProfileMenu />
            </Box>
        </Box>
  )
}

export { PrimarySearchAppBar }
