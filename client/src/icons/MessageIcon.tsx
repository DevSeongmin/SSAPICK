interface MessageIconProps {
  isHighlighted: boolean;
}

const MessageIcon = ({ isHighlighted }: MessageIconProps) => {
  return (
    <>
      {isHighlighted ? (
        <svg
          width="24"
          height="24"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M3.00034 10.4147C3 10.5691 3 10.7304 3 10.8993V15C3 17.8285 3 19.2427 3.87868 20.1213C4.75736 21 6.17157 21 9 21H15C17.8284 21 19.2426 21 20.1213 20.1213C21 19.2427 21 17.8285 21 15V10.8993C21 10.7304 21 10.5691 20.9997 10.4147L19.2929 12.1215C18.7303 12.6841 17.9672 13.0002 17.1716 13.0002H6.82843C6.03278 13.0002 5.26972 12.6841 4.70711 12.1215L3.00034 10.4147ZM3.23713 7.8231L6.12132 10.7073C6.30886 10.8948 6.56321 11.0002 6.82843 11.0002H17.1716C17.4368 11.0002 17.6911 10.8948 17.8787 10.7073L20.7629 7.8231C20.6991 7.63995 20.6182 7.47292 20.5155 7.3149C20.031 6.56934 19.1662 6.18502 17.4368 5.4164L17.4368 5.41639L13.6246 3.72205C12.8245 3.36646 12.4244 3.18866 12 3.18866C11.5756 3.18866 11.1755 3.36646 10.3754 3.72205L6.56317 5.41639C4.83375 6.18502 3.96905 6.56934 3.48452 7.3149C3.38183 7.47292 3.30091 7.63995 3.23713 7.8231Z"
            fill="#5F86E9"
          />
        </svg>
      ) : (
        <svg
          width="24"
          height="24"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <rect
            x="4.3335"
            y="6.5"
            width="17.3333"
            height="13"
            rx="1.02401"
            stroke="black"
            strokeWidth="2"
          />
          <path
            d="M4.3335 9.75L12.817 13.9917C12.9323 14.0494 13.068 14.0494 13.1833 13.9917L21.6668 9.75"
            stroke="black"
            strokeWidth="2"
          />
        </svg>
      )}
    </>
  );
};

export default MessageIcon;
