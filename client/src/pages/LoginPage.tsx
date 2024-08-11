import GoogleButton from "buttons/GoogleButton"
import KakaoButton from "buttons/KakaoButton"
import { useCookies } from "react-cookie"
import { useNavigate, useSearchParams } from "react-router-dom"
import LoginIcon from "../icons/LoginIcon"
import { useEffect } from "react"
import { refresh } from "api/authApi"
import { useSetRecoilState } from "recoil"
import { accessTokenState } from "atoms/UserAtoms"

const Login = () => {
    const setAccessToken = useSetRecoilState(accessTokenState);

    useEffect(() => {
        refresh()
        .then((response) => {
            setAccessToken(response.accessToken);
            
        })
        .catch((error) => {
            console.error(error);
        });
        
      }, [setAccessToken]
    );


    return <div className="flex flex-col  items-center mt-36">
        <LoginIcon />
        <span className="luckiest_guy text-color-5F86E9 text-4xl mt-10 mb-28">SSAPICK</span>
        <KakaoButton />
        <GoogleButton />
    </div>
}

export default Login