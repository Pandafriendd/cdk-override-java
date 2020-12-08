package com.myorg;

import software.amazon.awscdk.core.App;

public final class CdkClbJavaApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkClbJavaStack(app, "CdkClbJavaStack");
        
        new CDKS3Stack(app, "CDKS3Stack");

        app.synth();
    }
}
