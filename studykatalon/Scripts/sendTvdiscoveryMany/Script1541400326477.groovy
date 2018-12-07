import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder as RestRequestObjectBuilder
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent as HttpTextBodyContent

String endpoint = 'http://' + GlobalVariable.cmndIpAddress + ":8080/SmartInstall/webservices.jsp"

String requestMethod = 'POST'

String authHeader = 'whateverYouNeedForAuthentication'

TestObjectProperty header1 = new TestObjectProperty('Authorization', ConditionType.EQUALS, authHeader)

TestObjectProperty header2 = new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')

TestObjectProperty header3 = new TestObjectProperty('Accept', ConditionType.EQUALS, 'application/json')

ArrayList defaultHeaders = Arrays.asList(header1, header2, header3)

tvResponse1 = '{"CommandDetails":{"TVDiscoveryParameters":{"PowerStatus":"On","TVIPAddress":'

tvResponse2 = ',"TVModelNumber":"32HFL5011T/12","TVRoomID":'

tvResponse3 = ',"VSecureTVID":"80F4F91"},"WebServiceParameters":{"PollingFrequency":10,"TVUniqueID":'

tvResponse4 = '}},"CmdType":"Response","Cookie":293,"Fun":"TVDiscoveryService","Svc":"WebServices","SvcVer":"3.0"}'

RequestObject ro = new RestRequestObjectBuilder().withRestUrl(endpoint).withHttpHeaders(defaultHeaders).withRestRequestMethod(
    requestMethod).build()

for (def index : (1..GlobalVariable.tvDiscoveryCount)) {
    tvDiscovery = tvResponse1

    tvIp = ((('"' + '10.0.23.') + index) + '"')

    tvRoomId = checkRoomId(index)

    tvUniqueID = (('"TEST' + index) + '1C5A6BB5C57F"')

    tvDiscovery = (tvDiscovery + tvIp + generateMac(index) + tvResponse2 + tvRoomId 
		+ generateSnNumber(index) + tvResponse3 + tvUniqueID + tvResponse4)

    println('tvDiscovery:' + tvDiscovery)

    ro.setBodyContent(new HttpTextBodyContent(tvDiscovery))

    'Send a REST request after its URL has been changed'
    def response = WS.sendRequest(ro)

    responseStatusCode = response.getStatusCode()

    println('Response status code:' + responseStatusCode)

    WebUI.delay(GlobalVariable.tvDiscoveryFrequency)
}

String checkRoomId(Integer index) {
    String tvRoomId = '"00000"'

    if ((index > 0) && (index < 10)) {
        tvRoomId = (('"0000' + index) + '"')
    } else if ((index >= 10) && (index < 100)) {
        tvRoomId = (('"000' + index) + '"')
    } else if ((index >= 100) && (index < 1000)) {
        tvRoomId = (('"00' + index) + '"')
    }
    
    return tvRoomId
}

String generateSnNumber(Integer index) {
	String tvSerialNumber = ",\"TVSerialNumber\":\"FZ1A16140605" + index + "\""
	return tvSerialNumber
}

String generateMac(Integer index) {
	String macAddress = ",\"TVMACAddress\":\"1C:5A:6B:B5:C5:7F\""
	if (index > 0 && index < 10) {
		macAddress = ",\"TVMACAddress\":\"" + index + "C:5A:6B:B5:C5:7F\""
	} else if (index >= 10 && index < 100) {
		macAddress = ",\"TVMACAddress\":\"1C:" + index%10 + "A:6B:B5:C5:7F\""
	} else if (index >= 100 && index < 1000) {
		macAddress = ",\"TVMACAddress\":\"1C:5A:" + index%100 + "B:B5:C5:7F\""
	}
	return macAddress
}

