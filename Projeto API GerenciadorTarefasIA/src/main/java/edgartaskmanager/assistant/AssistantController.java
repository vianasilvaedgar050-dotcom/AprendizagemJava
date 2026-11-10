package edgartaskmanager.assistant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/assistant")
@Validated
public class AssistantController {

    private final BudgetAssistantService assistantService;

    public AssistantController(BudgetAssistantService assistantService) {
        this.assistantService = assistantService;
    }

    @PostMapping("/prompt")
    public BudgetAssistantService.AssistantResponse processPrompt(
            @Valid @RequestBody PromptRequest request) {
        return assistantService.processText(request.prompt());
    }

    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BudgetAssistantService.AssistantResponse processAudio(
            @RequestParam("file") MultipartFile file) throws java.io.IOException {
        return assistantService.processAudio(file);
    }

    public record PromptRequest(@NotBlank String prompt) {
    }
}
