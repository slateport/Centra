import React from "react";
import styled, { createGlobalStyle } from "styled-components";
import { GlobalStyleProps } from '../types/types'
import { PrimarySearchAppBar } from "../components/Header";

import { spacing } from "@material-ui/system";
import {
    CssBaseline,
    Paper as MuiPaper,
} from "@material-ui/core";


const GlobalStyle = createGlobalStyle<GlobalStyleProps>`
  html,
  body,
  #root {
    height: 100%;
  }

  body {
    background: ${props => props.theme.body.background};
  }

  .MuiCardHeader-action .MuiIconButton-root {
    padding: 4px;
    width: 28px;
    height: 28px;
  }
`;

const Root = styled.div`
  display: flex;
  min-height: 100vh;
`;

const AppContent = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

const Paper = styled(MuiPaper)(spacing);

const MainContent = styled(Paper)`
  flex: 1;
  background: ${props => props.theme.body.background};
  padding: 20px;

  @media all and (-ms-high-contrast: none), (-ms-high-contrast: active) {
    flex: none;
  }

  .MuiPaper-root .MuiPaper-root {
    box-shadow: none;
  }
`;

const ApplicationLayout = ({ props, init, children }) => {
    return (
        <Root>
            <CssBaseline />
            <GlobalStyle />
            <AppContent>
                <PrimarySearchAppBar initData={init} />
                <MainContent>
                    {children}
                </MainContent>
            </AppContent>

        </Root>
    )
}
export { ApplicationLayout };
