import { Link, Outlet, useLocation } from 'react-router-dom';

const QuestionList = () => {
  const location = useLocation().pathname.split('/')[3];
  return (
    <div className="m-5">
      <div className="flex flex-row justify-center">
        <Link
          to="questioninfo"
          className={`border-b-2 w-1/2 text-center pb-3 text-lg ${location === 'questioninfo' || location === undefined ? 'text-color-5F86E9 border-[#5F86E9]' : 'text-white border-white'}`}
        >
          질문 리스트
        </Link>
        <Link
          to="makequestion"
          className={`border-b-2 w-1/2 text-center pb-3 text-lg ${location === 'makequestion' ? 'text-color-5F86E9 border-[#5F86E9]' : 'text-white border-white'}`}
        >
          생성한 질문
        </Link>
      </div>
      <div className="mt-5">
        <Outlet />
      </div>
    </div>
  );
}

export default QuestionList;