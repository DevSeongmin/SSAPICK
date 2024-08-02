import instance from 'api/clientApi';
import { IPick, IPickCreate } from 'atoms/Pick.type';
import { BaseResponse } from 'atoms/User.type';

// 받은 pick 조회
export const getReceivePick = async (): Promise<IPick[]> => {
  const {
    data: { success, data },
  } = await instance.get<BaseResponse<IPick[]>>('/pick/receive');

  if (!success) {
    throw new Error('받은 pick 조회 실패');
  }

  return data;
};

// pick 생성
export const postCreatePick = async (pickData: IPickCreate): Promise<void> => {
  const {
    data: { success },
  } = await instance.post('/pick', pickData);

  if (!success) {
    throw new Error('pick 생성 실패');
  }
};
