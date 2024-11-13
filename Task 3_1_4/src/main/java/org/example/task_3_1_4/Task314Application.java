package org.example.task_3_1_4;

import org.example.task_3_1_4.model.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class Task314Application {

	private static final String BASE_URL = "http://94.198.50.185:7081/api/users";
	private static String sessionId;

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		// 1. Получить список всех пользователей и сохранить session ID
		ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL, String.class);
		System.out.println("Список пользователей: " + response.getBody());

		// Получаем sessionId из заголовка Set-Cookie
		if (response.getHeaders().containsKey("Set-Cookie")) {
			sessionId = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
		}
		System.out.println("SessionId: " + sessionId);

		// 2. Сохранить пользователя
		User newUser = new User(3L, "James", "Brown", (byte) 25);
		HttpHeaders postHeaders = createHeaders(sessionId);
		HttpEntity<User> postRequest = new HttpEntity<>(newUser, postHeaders);

		ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL, postRequest, String.class);
		System.out.println("Ответ при добавлении пользователя: " + postResponse.getBody());

		// Получаем первую часть кода из ответа
		String firstCodePart = postResponse.getBody(); // Измените это, если необходимо

		// 3. Изменить пользователя
		newUser.setName("Thomas");
		newUser.setLastName("Shelby");
		HttpHeaders putHeaders = createHeaders(sessionId);
		HttpEntity<User> putRequest = new HttpEntity<>(newUser, putHeaders);

		ResponseEntity<String> putResponse = restTemplate.exchange(BASE_URL, HttpMethod.PUT, putRequest, String.class);
		System.out.println("Ответ при обновлении пользователя: " + putResponse.getBody());

		// Получаем вторую часть кода
		String secondCodePart = putResponse.getBody(); // Измените это, если необходимо

		// 4. Удалить пользователя
		HttpHeaders deleteHeaders = createHeaders(sessionId);
		HttpEntity<Void> deleteRequest = new HttpEntity<>(deleteHeaders);

		ResponseEntity<String> deleteResponse = restTemplate.exchange(BASE_URL + "/3", HttpMethod.DELETE, deleteRequest, String.class);
		System.out.println("Ответ при удалении пользователя: " + deleteResponse.getBody());

		// Получаем третью часть кода
		String thirdCodePart = deleteResponse.getBody(); // Измените это, если необходимо

		// Конкатенируем все части кода в одной строке
		String finalCode = (firstCodePart + secondCodePart + thirdCodePart);
		System.out.println("Итоговый код (числа): " + finalCode);




		System.out.println("Финальный код (18 символов): " + finalCode);
	}

	private static HttpHeaders createHeaders(String sessionId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", sessionId);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}


