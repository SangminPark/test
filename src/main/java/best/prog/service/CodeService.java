package best.prog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Code;
import best.prog.repository.CodeRepository;


@Service
@Transactional
public class CodeService {

    @Autowired
    CodeRepository codeRepository;

    /**
     * 생성
     */
    public Code create(Code code) {
        validateDuplicateCode(code);
        codeRepository.save(code);
        return code;
    }

    private void validateDuplicateCode(Code code) {
        Code findCode = codeRepository.findByCode(code.getCode());
        if (findCode != null) {
            throw new IllegalStateException("이미 존재하는 Code입니다.");
        }
    }
    
    /**
     * 수정
     * @param code
     * @return
     */
    public Code update(Code code) {
    	Code findCode = codeRepository.findOneWithSubCode(code.getUid());
    	findCode.setCode(code.getCode());
    	findCode.setName(code.getName());
      codeRepository.saveAndFlush(findCode);
    	return findCode;
    }

    /**
     * 삭제
     * @param code
     * @return
     */
    public void delete(Code code) {    	
    	codeRepository.delete(code);
    }

    /**
     * 정보 조회
     * @param code
     * @return
     */
    public Code findCode(Code code) {
      return codeRepository.findOne(code.getUid());
    }

    /**
     * 코드정보와 서브코드 목록을 함께 조회
     * @param code
     * @return
     */
    public Code findOneWithSubCode(Code code) {
        return codeRepository.findOneWithSubCode(code.getUid());
    }

    /**
     * 전체 조회
     */
    public List<Code> findCodes() {
        return codeRepository.findAll();
    }

}
