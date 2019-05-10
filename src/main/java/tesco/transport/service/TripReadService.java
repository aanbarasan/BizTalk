/**
 * 
 */
package tesco.transport.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tesco.transport.dao.TrackingDAO;
import tesco.transport.dto.SiteInformation;

/**
 * @author ndevados
 *
 */
@Service
public class TripReadService {

	@Autowired
	TrackingDAO trackingDao;

	public void readFiles(String inpath) {

		List<SiteInformation> sitelist = new ArrayList<>();
		SiteInformation siteInformation = new SiteInformation();
		String routeId = null;
		File Folder = new File(inpath);
		File files[];
		files = Folder.listFiles();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			if (files.length > 0) {

				for (File file : files) {

					Document doc = dBuilder.parse(file);

					doc.getDocumentElement().normalize();

					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					NodeList nList = doc.getElementsByTagName("journey");

					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);
						Element eElement = (Element) nNode;

						System.out.println(nNode.getNodeType());

						NodeList siteList = eElement.getElementsByTagName("site");

						for (int j = 0; j < siteList.getLength(); j++) {
							Node siteNode = siteList.item(j);
							Element siteElement = (Element) siteNode;
							sitelist.add(setValueToPojo(eElement, siteElement, siteInformation));
							routeId = siteInformation.getRouteId();
							siteInformation = new SiteInformation();
						}

					}
					System.out.println(sitelist.size() + "RouteID->" + routeId);
				}
				trackingDao.addSiteInfo(sitelist, routeId);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private SiteInformation setValueToPojo(Element eElement, Element siteElement, SiteInformation siteInformation) {

		siteInformation.setRouteId(eElement.getElementsByTagName("routeId").item(0).getTextContent());
		siteInformation.setJourneyAlias(eElement.getElementsByTagName("journeyAlias").item(0).getTextContent());
		siteInformation.setJourneyStart(eElement.getElementsByTagName("journeyStart").item(0).getTextContent());
		siteInformation.setPlannedDistance(eElement.getElementsByTagName("plannedDistance").item(0).getTextContent());

		if (!StringUtils.isEmpty(siteElement.getAttribute("id"))) {
			siteInformation.setSite(siteElement.getAttribute("id"));
		}

		NodeList resourceList = eElement.getElementsByTagName("resource");
		for (int k = 0; k < resourceList.getLength(); k++) {
			Node resourceNode = resourceList.item(k);
			Element resourceElement = (Element) resourceNode;

			if ("DRIVER".equalsIgnoreCase(resourceElement.getAttribute("type"))) {
				siteInformation.setResourceDriver(resourceElement.getAttribute("id"));
			} else if ("VEHICLE".equalsIgnoreCase(resourceElement.getAttribute("type"))) {
				siteInformation.setVehicle(resourceElement.getAttribute("id"));
			} else {
				System.out.println("SiteId" + resourceElement.getAttribute("id"));
			}
			System.out.println("Id" + resourceElement.getAttribute("id"));
			System.out.println("type: " + resourceElement.getAttribute("type"));
		}

		siteInformation.setPta(siteElement.getElementsByTagName("pta").item(0).getTextContent());
		siteInformation.setPtd(siteElement.getElementsByTagName("ptd").item(0).getTextContent());
		siteInformation.setCallSequence(siteElement.getElementsByTagName("callSequence").item(0).getTextContent());
		siteInformation.setSiteName(siteElement.getElementsByTagName("siteName").item(0).getTextContent());
		siteInformation.setSiteCategory(siteElement.getElementsByTagName("siteCategory").item(0).getTextContent());
		siteInformation.setSiteAddress1(siteElement.getElementsByTagName("siteAddress1").item(0).getTextContent());
		siteInformation.setSiteCity(siteElement.getElementsByTagName("siteCity").item(0).getTextContent());
		siteInformation.setSitePostCode(siteElement.getElementsByTagName("sitePostCode").item(0).getTextContent());
		siteInformation.setLatitude(siteElement.getElementsByTagName("latitude").item(0).getTextContent());
		siteInformation.setLongitude(siteElement.getElementsByTagName("longitude").item(0).getTextContent());

		return siteInformation;

	}

}
