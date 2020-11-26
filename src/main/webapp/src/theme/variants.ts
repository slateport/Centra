import {blue, grey} from '@material-ui/core/colors'

const darkVariant = {
  name: 'Dark',
  palette: {
    primary: {
      main: blue[700],
      contrastText: '#FFF'
    },
    secondary: {
      main: blue[500],
      contrastText: '#FFF'
    }
  },
  header: {
    color: grey[100],
    background: blue[900],
    search: {
      color: '#FFFFFF'
    },
    indicator: {
      background: blue[600]
    },
    brand: {
      color: grey[100]
    }
  },
  body: {
    background: '#F7F9FC'
  }
}

const variants: Array<VariantType> = [
  darkVariant
]

export default variants

export type VariantType = {
  name: string
  palette: {
      primary: MainContrastTextType
      secondary: MainContrastTextType
  }
  header: ColorBgType & {
      search: {
          color: string
      }
      indicator: {
          background: string
      }
  }
  body: {
      background: string
  }
}

type MainContrastTextType = {
  main: string
  contrastText: string
}
type ColorBgType = {
  color: string
  background: string
}
