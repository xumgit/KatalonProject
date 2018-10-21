import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

'open browser'
WebUI.openBrowser('')

WebUI.waitForPageLoad(1)

'redirect url to smartinstall'
WebUI.navigateToUrl('http://localhost:8080/cas/login?service=http%3A%2F%2Flocalhost%3A8080%2FSmartInstall%2Flogin%2Fcas')

WebUI.waitForPageLoad(1)

'enter user name'
WebUI.setText(findTestObject('CMND/Page_CAS_Enter_UserName/input_Enter Username'), 'admin')

'enter password'
WebUI.setText(findTestObject('CMND/Page_CAS_Enter_Password/input_Enter password'), 'tpvision')

'login to cmnd'
WebUI.click(findTestObject('CMND/Page_CAS_Login/input_login'))

WebUI.waitForPageLoad(5)

'select clone'
WebUI.click(findTestObject('CMND/Page_select_clone_data/span_None'))

WebUI.waitForPageLoad(3)

'slect none clone data'
WebUI.click(findTestObject('CMND/Page_assign_none_clonedata/span_None'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForPageLoad(3)

'select clone again'
WebUI.click(findTestObject('CMND/Page_select_clone_data/span_None'))

WebUI.waitForPageLoad(3)

'slect 5388 clone data'
WebUI.click(findTestObject('CMND/Page_Select-clonedata_5388/span_TPM181HE_5388'))

WebUI.waitForPageLoad(5)

WebUI.waitForElementNotPresent(findTestObject('CMND/Page_select_clonedata_tip/div_Just a moment selecting cl'), 10)

WebUI.waitForPageLoad(5)

'start upgarde'
WebUI.click(findTestObject('CMND/Page_click_upgrade/span_TPM181HE_5388 101718 852'))

WebUI.waitForPageLoad(2)

'wait icon rotate, timeout is 5 seconds'
WebUI.waitForElementVisible(findTestObject('CMND/Page_upgrade_INP/div_TPM181HE_5388 101718 852 A'), 5)

WebUI.waitForPageLoad(2)

'end upgrade, means success'
WebUI.waitForElementVisible(findTestObject('CMND/Page_upgrade_success_ST/div_TPM181HE_5388 101718 852 A'), 20)

