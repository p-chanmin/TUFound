import React from "react";
import Header from "../Components/Header";
import Box from "@mui/material/Box";
import Carousel from "react-material-ui-carousel";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import { Button } from "@mui/material";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Link from "@mui/material/Link";

const Main = () => {
  return (
    <Container component="main" maxWidth="xs">
      <header>
        <Header></Header>
      </header>

      <Container>
        <Box sx={{ my: 5 }}>
          <Typography component="h1" variant="h5">
            [분실탭]
          </Typography>
          <Button type="submit"></Button>
        </Box>
      </Container>

      <Carousel>
        <Card sx={{ maxWidth: 345 }}>
          <CardMedia component="img" height="140" image="https://sineorb3.cafe24.com/Design/mall_2/600/BRAUN/BN0032WHBKG.jpg" alt="시계" />
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              시계
            </Typography>
            <Typography variant="body2" color="text.secondary">
              시계를 잃어버렸어요... 3시쯤에 TIP에서 잃어버린것같은데 찾아주세요.
            </Typography>
          </CardContent>
          <CardActions>
            <Link href="/">제목</Link>
            <Link href="/">장소/날짜</Link>
          </CardActions>
        </Card>

        <Card sx={{ maxWidth: 345 }}>
          <CardMedia
            component="img"
            height="140"
            image="https://mblogthumb-phinf.pstatic.net/MjAyMDA1MTZfMTU0/MDAxNTg5NjI1MTk2OTM5.e_Yb-xFWVdU8bTef-9UyF1prnEvhJe47RLYN7sPicckg.ZS6Q5c4p6gRX7aHw8TtSABCKBk6okAI09bTN50V6MSgg.JPEG.atom8532/1589625195687.jpg?type=w800"
            alt="포르쉐"
          />
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              포르쉐
            </Typography>
            <Typography variant="body2" color="text.secondary">
              누가 내차에다가 빵꾸내놨냐
            </Typography>
          </CardContent>
          <CardActions>
            <Link href="/">제목</Link>
            <Link href="/">장소/날짜</Link>
          </CardActions>
        </Card>
      </Carousel>

      <Container>
        <Box sx={{ my: 5 }}>
          <Typography component="h1" variant="h5">
            [습득탭]
          </Typography>
        </Box>
      </Container>
      <Carousel>
        <Card sx={{ maxWidth: 345 }}>
          <CardMedia component="img" height="140" image="https://sineorb3.cafe24.com/Design/mall_2/600/BRAUN/BN0032WHBKG.jpg" alt="시계" />
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              시계
            </Typography>
            <Typography variant="body2" color="text.secondary">
              시계를 찾았어요
            </Typography>
          </CardContent>
          <CardActions>
            <Link href="/">제목</Link>
            <Link href="/">장소/날짜</Link>
          </CardActions>
        </Card>

        <Card sx={{ maxWidth: 345 }}>
          <CardMedia
            component="img"
            height="140"
            image="https://mblogthumb-phinf.pstatic.net/MjAyMDA1MTZfMTU0/MDAxNTg5NjI1MTk2OTM5.e_Yb-xFWVdU8bTef-9UyF1prnEvhJe47RLYN7sPicckg.ZS6Q5c4p6gRX7aHw8TtSABCKBk6okAI09bTN50V6MSgg.JPEG.atom8532/1589625195687.jpg?type=w800"
            alt="포르쉐"
          />
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              포르쉐
            </Typography>
            <Typography variant="body2" color="text.secondary">
              포르쉐를 찾았어요
            </Typography>
          </CardContent>
          <CardActions>
            <Link href="/">제목</Link>
            <Link href="/">장소/날짜</Link>
          </CardActions>
        </Card>
      </Carousel>
    </Container>
  );
};

export default Main;
