package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

	@Autowired
	private HttpServletRequest request;

	public String upload(String baseFolder, MultipartFile file) {
		// TODO criar teste para esse método...		
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
