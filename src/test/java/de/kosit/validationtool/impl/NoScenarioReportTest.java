package de.kosit.validationtool.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;

import org.junit.Test;

import de.kosit.validationtool.api.CheckConfiguration;
import de.kosit.validationtool.api.InputFactory;
import de.kosit.validationtool.api.Result;
import de.kosit.validationtool.impl.Helper.Simple;
import de.kosit.validationtool.impl.Helper.UBL;
import de.kosit.validationtool.model.noscenario.NoScenarioFound;

/**
 * Testet NoScenarioReport-Konfiguration.
 * 
 * @author Andreas Penski
 */
public class NoScenarioReportTest {

    @Test
    public void testNoConfig() throws MalformedURLException {
        // die config ist ab Version 2 optional.
        final CheckConfiguration d = new CheckConfiguration(Simple.NO_SCNENARIO_REPORT_SCENARIOS);
        d.setScenarioRepository(Simple.REPOSITORY);
        final DefaultCheck check = new DefaultCheck(d);
        final Result result = check.checkInput(InputFactory.read(UBL.SAMPLE.toURL()));
        assertThat(result.isAcceptable()).isFalse();
        assertThat(result.getReportDocument().getDocumentElement().getNodeName()).contains(NoScenarioFound.class.getSimpleName());
    }

    @Test
    public void testWithConfig() throws MalformedURLException {
        final CheckConfiguration d = new CheckConfiguration(Simple.SCENARIOS);
        d.setScenarioRepository(Simple.REPOSITORY);
        final DefaultCheck check = new DefaultCheck(d);
        final Result result = check.checkInput(InputFactory.read(UBL.SAMPLE.toURL()));
        assertThat(result.isAcceptable()).isFalse();
        assertThat(result.getReportDocument().getDocumentElement().getNodeName()).isEqualTo("no-match");
    }
}
