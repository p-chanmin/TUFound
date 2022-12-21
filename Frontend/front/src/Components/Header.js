import React, { useState } from "react";
import { AppBar, Button, CssBaseline, Tab, Tabs, Toolbar } from "@mui/material";
import Avatar from "@mui/material/Avatar";
import Slide from "@mui/material/Slide";
import useScrollTrigger from "@mui/material/useScrollTrigger";

function HideOnScroll(props) {
  const { children, window } = props;
  const trigger = useScrollTrigger({
    target: window ? window() : undefined,
  });

  return (
    <Slide appear={false} direction="down" in={!trigger}>
      {children}
    </Slide>
  );
}

const Header = (props) => {
  const [value, setValue] = useState("1");
  return (
    <React.Fragment>
      <CssBaseline />
      <HideOnScroll {...props}>
        <AppBar sx={{ background: "#063970" }}>
          <Toolbar>
            <Avatar sx={{ m: 1, width: 56, height: 56, border: "3px solid black" }}>
              <img
                src="https://www.tukorea.ac.kr/sites/tukorea/images/sub/symbol_mark_8_3.jpg"
                alt="한국공학대학교"
                width={55}
                height={55}
              ></img>
            </Avatar>
            <Tabs
              sx={{ marginLeft: "auto" }}
              textColor="inherit"
              value={value}
              onChange={(e, value) => setValue(value)}
              indicatorColor="primary"
            >
              <Tab label="게시물" />
              <Tab label="글쓰기" />
              <Tab label="쪽지" />
              <Tab label="쪽지보관" />
            </Tabs>
            <Button type="submit" sx={{ marginLeft: "auto" }} variant="contained">
              로그아웃{" "}
            </Button>
          </Toolbar>
        </AppBar>
      </HideOnScroll>
      <Toolbar />
    </React.Fragment>
  );
};

export default Header;
