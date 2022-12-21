import React from "react";
import { Button, ButtonGroup, Grid, TextField } from "@mui/material";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import Link from "@mui/material/Link";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Avatar from "@mui/material/Avatar";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";

const Signup = () => {
  const theme = createTheme();

  const handleSubmit = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    console.log({
      email: data.get("email"),
      password: data.get("password"),
    });
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, width: 56, height: 56, border: "3px solid black" }}>
            <img
              src="https://www.tukorea.ac.kr/sites/tukorea/images/sub/symbol_mark_8_3.jpg"
              alt="한국공학대학교"
              width={55}
              height={55}
            ></img>
          </Avatar>
          <Typography component="h1" variant="h5">
            TUFOUND 회원가입
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField id="email" label="이메일" name="email" required fullWidth autoComplete="email"></TextField>
                <p>학교 이메일은 @tukorea.ac.kr 형식으로 기입해주세요 !</p>
              </Grid>
              <Grid item xs={12}>
                <TextField id="password" label="비밀번호" name="password" required fullWidth autoComplete="new-password"></TextField>
                <p>영문과 숫자 조합의 8-20자의 비밀번호를 설정해주세요. 특수문자(!@#$%^&*)도 사용 가능합니다.</p>
              </Grid>

              <Grid item xs={12}>
                <FormControlLabel
                  control={<Checkbox value="allowExtraEmails" color="primary" />}
                  label="한국공학대학교 사랑하면 체크(아님말고)"
                />
              </Grid>
            </Grid>
            <Box textAlign="center">
              <ButtonGroup sx={{ mt: 3, mb: 2 }} fullWidth>
                <Button size="Medium" type="submit" variant="contained">
                  회원가입
                </Button>
                <Button size="Medium" variant="outlined" fullWidth>
                  취소
                </Button>
              </ButtonGroup>
            </Box>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/login" variant="body2">
                  이미 계정이 있으신가요? 로그인창으로 이동합니다.
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default Signup;
