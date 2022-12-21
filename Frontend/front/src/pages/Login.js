import React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Checkbox from "@mui/material/Checkbox";
import Button from "@mui/material/Button";
import FormControlLabel from "@mui/material/FormControlLabel";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Avatar from "@mui/material/Avatar";
import Container from "@mui/material/Container";

const login = () => {
  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 3,
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
          학교에서 잃어버린 물건이 있으신가요?
        </Typography>
        <TextField label="이메일" required fullWidth name="email" autoComplete="email" margin="normal" />
        <TextField label="비밀번호" type="password" required fullWidth name="password" autoComplete="current-password" margin="normal" />
        <FormControlLabel control={<Checkbox value="remember" color="primary" />} label="이메일 저장" />
        <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
          Sign in
        </Button>
        <Grid container>
          <Grid item xs>
            <Link href="/login/signup">(비회원전용)TUKOREA 이메일 인증</Link>
          </Grid>
        </Grid>
      </Box>
    </Container>
  );
};

export default login;
