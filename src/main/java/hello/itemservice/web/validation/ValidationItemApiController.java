package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @RequestMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {

        // @ModelAttribute 는 각각의 필드를 세밀하게 검증 적용 -> 다른 필드가 바인딩이 실패해도 나머지는 검증 실행
        // @RequestBody 는 모든 필드가 바인딩이 되어야 검증 실행 -> 하나라도 바인딩이 실패하면 예외 발생
        log.info("API 컨트롤러 호출");
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생, errors = {}", bindingResult);
            // 실무에서는 검증 실패 반환 객체를 정의해서 API 명세 후 필요한 내용만 반환
            return bindingResult.getAllErrors();
        }
        log.info("성공 로직 실행");

        return form;
    }

}
