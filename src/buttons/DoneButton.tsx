interface DoneButtonProps {
  title: string;

}

const DoneButton = ({title}:DoneButtonProps) => (
  <button type="submit" className="text-white background-color-5F86E9 font-medium rounded-lg text-sm my-4 px-5 py-2.5 " >{title}</button>
);

export default DoneButton;
