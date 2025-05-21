rootProject.name = "msa-root"

// infra
include("msa-discovery")
include("msa-gateway")

// application
include("msa-user")
include("msa-catalog")
include("msa-order")
