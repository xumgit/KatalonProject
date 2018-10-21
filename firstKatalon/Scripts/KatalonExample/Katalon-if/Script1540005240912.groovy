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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('http://www.baidu.com')

varA = true

WebUI.waitForPageLoad(10)

aboutBaidu = WebUI.getText(findTestObject('KatalonTestCase/Page_baidu_About_Text/a_AboutBaidu'))

WebUI.waitForPageLoad(1)

println('aboutBaidu=' + aboutBaidu)

resultIsEqual = WebUI.verifyEqual(aboutBaidu, 'About  Baidu')

println('resultIsEqual=' + resultIsEqual)

if (varA == true) {
    WebUI.setText(findTestObject('KatalonTestCase/Page_baidu_input/input__wd'), 'baidu')

    WebUI.click(findTestObject('KatalonTestCase/Page_baidu_click_button/input__su'))
} else if (varA == false) {
    WebUI.setText(findTestObject('KatalonTestCase/Page_baidu_input/input__wd'), 'katalon')

    WebUI.click(findTestObject('KatalonTestCase/Page_baidu_click_button/input__su'))
} else {
    WebUI.setText(findTestObject(null), 'cmd')

    WebUI.click(findTestObject('KatalonTestCase/Page_baidu_click_button/input__su'))
}

