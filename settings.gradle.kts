rootProject.name = "msa-root"

// infra
include("msa-discovery")
include("msa-gateway")
include("msa-config")

// application
include("msa-user")
include("msa-catalog")
include("msa-order")
