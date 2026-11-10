package edgartaskmanager.assistant;

import edgartaskmanager.transaction.TransactionTools;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BudgetAssistantService {

    private final ChatClient chatClient;
    private final TranscriptionModel transcriptionModel;
    private final TransactionTools transactionTools;

    public BudgetAssistantService(
            ChatClient.Builder chatClientBuilder,
            TranscriptionModel transcriptionModel,
            TransactionTools transactionTools) {
        this.chatClient = chatClientBuilder.build();
        this.transcriptionModel = transcriptionModel;
        this.transactionTools = transactionTools;
    }

    public AssistantResponse processText(String prompt) {
        String response = chatClient.prompt()
                .system("""
                        Você é um assistente de orçamento financeiro. Responda em português.
                        Registre uma transação somente quando o usuário pedir explicitamente e informar
                        descrição, valor e categoria. Nunca invente dados financeiros.
                        """)
                .user(prompt)
                .tools(transactionTools)
                .call()
                .content();
        return new AssistantResponse(response, null);
    }

    public AssistantResponse processAudio(MultipartFile audioFile) throws IOException {
        if (audioFile.isEmpty()) {
            throw new IllegalArgumentException("O arquivo de áudio não pode estar vazio.");
        }

        ByteArrayResource audioResource = new ByteArrayResource(audioFile.getBytes()) {
            @Override
            public String getFilename() {
                return audioFile.getOriginalFilename() == null
                        ? "audio"
                        : audioFile.getOriginalFilename();
            }
        };
        String transcript = transcriptionModel.transcribe(audioResource);
        AssistantResponse response = processText(transcript);
        return new AssistantResponse(response.response(), transcript);
    }

    public record AssistantResponse(String response, String transcription) {
    }
}
