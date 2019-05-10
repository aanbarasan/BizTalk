package com.steria.BizTalk.RestContoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steria.BizTalk.service.TripReadService;

/**
 * @author ndevados
 *
 */
@RestController
public class FileReadController {

	@Value("${processed.folder.path}")
	private String processedDirPath;

	@Autowired
	TripReadService tripReadObj;

	@RequestMapping("/readFiles")
	public void readefile() {

		// String path = "D:/Nanda/TransportTescoProject/xmlFiles";

		tripReadObj.readFiles(processedDirPath);

	}

}